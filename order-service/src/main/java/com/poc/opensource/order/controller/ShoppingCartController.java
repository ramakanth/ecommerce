package com.poc.opensource.order.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.opensource.order.vo.ShoppingCartVO;
import com.poc.opensource.order.commons.keygen.service.SequenceGeneratorService;
import com.poc.opensource.order.entity.LineItem;
import com.poc.opensource.order.feign.CustomerServiceClient;
import com.poc.opensource.order.feign.ProductServiceClient;
import com.poc.opensource.order.service.ShoppingCartService;
import com.poc.opensource.order.validator.ShoppingCartValidator;
import com.poc.opensource.order.vo.AddressVO;
import com.poc.opensource.order.vo.CustomerVO;
import com.poc.opensource.order.vo.LineItemVO;
import com.poc.opensource.order.vo.OrderVO;
import com.poc.opensource.order.vo.ProductRequestWrapper;
import com.poc.opensource.order.vo.ProductVO;
import com.poc.opensource.order.vo.ShoppingCartResponse;

@RestController
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ShoppingCartValidator validator;
	
	@Autowired
	private CustomerServiceClient customerServiceClient;
	@Autowired
	private ProductServiceClient productServiceClient;
    @Autowired
    private SequenceGeneratorService sequence;
	@PostMapping("/placeOrder")
	public ResponseEntity<ShoppingCartResponse> placeOrder(@Valid @RequestBody ShoppingCartVO shoppingCartVO){
		validator.validateInput(shoppingCartVO);
		CustomerVO customer = customerServiceClient.getCustomer(shoppingCartVO.getCustomerId());
		ProductRequestWrapper wrapper = new ProductRequestWrapper();
		wrapper.setIds(shoppingCartVO.getProductId());
		List<ProductVO> products = productServiceClient.getProducts(wrapper);
		OrderVO order = new OrderVO();
		order.setPayment(shoppingCartVO.getPayment());
	    this.address.apply(customer,order);
	    OrderVO orderVO = this.lineItem.apply(products, order);
	    OrderVO placeOrder = shoppingCartService.placeOrder(orderVO);
		
		ShoppingCartResponse response = new ShoppingCartResponse(placeOrder.getOrderId(),
				placeOrder.getShippingDate(),placeOrder.getOrderStatus(),
				placeOrder.getOrderTotal());
		return new ResponseEntity<ShoppingCartResponse>(response,HttpStatus.OK);
	}
	
	private BiFunction<CustomerVO, OrderVO,OrderVO>  address = (CustomerVO customerVO, OrderVO order)->{
		Optional<AddressVO> billAdress = customerVO.getAddresses().stream().
				filter(address -> address.getAddressType().equals("B"))
				.findFirst();
		Optional<AddressVO> shipAdress = customerVO.getAddresses().stream().
				filter(address -> address.getAddressType().equals("S"))
				.findFirst();
		if(shipAdress.isPresent()) {
			order.setShipAddress(shipAdress.get());
		}
		if(billAdress.isPresent()) {
			order.setBillAddress(billAdress.get());
		}
		order.setCustomerId(customerVO.getCustomerId());
		return order;
	};
	
	private BiFunction<List<ProductVO>, OrderVO,OrderVO> lineItem = (List<ProductVO> productVOList,OrderVO order) ->{
		 double taxPercentage = 8.00;
		if(productVOList.size() > 0) {
			double totalItemsPrice = productVOList.stream().mapToDouble(ProductVO::getPrice).sum();
			order.setItemsTotalPrice(totalItemsPrice);
			double taxAmount = (totalItemsPrice * taxPercentage)/100.00;
			order.setTaxAmt(taxAmount);
			double totalOrderAmt = totalItemsPrice + taxAmount;
			order.setOrderTotal(totalOrderAmt);
			
			List<LineItemVO> lineitems = new ArrayList<LineItemVO>();
			for(ProductVO product : productVOList) {
				LineItemVO itemVO = new LineItemVO();
				itemVO.setLineItemId(sequence.generateSequence(LineItem.LINE_ITEM_ID_SEQ));
				itemVO.setProductId(product.getProductId());
				itemVO.setProductName(product.getProductName());
				itemVO.setDescription(product.getDescription());
				itemVO.setPrice(product.getPrice());
				lineitems.add(itemVO);
			}
			order.setLineItems(lineitems);
			
			order.setOrderStatus("CONFIRM");
			LocalDateTime now = LocalDateTime.now();
			order.setOrderDate(now);
			order.setShippingMethod("STANDARD");
			order.setShippingDate(now.plusDays(7));
		}
		return order;
	};
}
