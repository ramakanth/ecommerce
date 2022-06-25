package com.poc.opensource.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.poc.opensource.order.entity.Order;
import com.poc.opensource.order.exception.OrderNotFoundException;
import com.poc.opensource.order.repository.IOrderRepository;
import com.poc.opensource.order.repository.OrderRepository;
import com.poc.opensource.order.vo.OrderVO;
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository repository;
	@Autowired
	private IOrderRepository iRepository;
    @Autowired
	private ModelMapper modelMapper;
	@Override
	public OrderVO createOrder(OrderVO orderVO) {
		
		if(!Optional.ofNullable(orderVO.getOrderDate()).isPresent()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "order date is blank");
	    }
	Order order = modelMapper.map(orderVO, Order.class);
	return modelMapper.map(repository.save(order),OrderVO.class);
	}
	@Override
	public List<OrderVO> getAllOrders() {
		List<Order> orders = repository.findAll();
		List<OrderVO> orderVos = orders.stream().map(order ->modelMapper.map(order, OrderVO.class)).collect(Collectors.toList());
		return orderVos;
	}
	@Override
	public OrderVO getOrderById(long orderId) {
		Optional<Order> product = repository.findById(orderId);
		OrderVO orderVO = modelMapper.map(product.get(),OrderVO.class);
		return orderVO;
	}
	@Override
	public OrderVO updateOrder(OrderVO orderVO) {
		Optional<Order> productDb = repository.findById(orderVO.getOrderId());
		if(!productDb.isPresent()) {
			throw new OrderNotFoundException("Order with order ID :  (" + orderVO.getOrderId() + ") not found!");
		}
		Order order = productDb.get();
		BeanUtils.copyProperties(orderVO, order);
		return modelMapper.map(repository.save(order), OrderVO.class);
	}
	@Override
	public void deleteOrderById(long orderId) {
		Optional<Order> order = repository.findById(orderId);
		if(!order.isPresent()) {
			throw new OrderNotFoundException("Order with order ID :  (" + orderId+ ") not found!");
		}
		repository.delete(order.get());
		
	}
	@Override
	public int getAllOrdersCount() {
		return 0;
	}
	@Override
	public List<OrderVO> getOrdersByDate(LocalDateTime date) {
		 List<Order> orders = iRepository.getOrdersByDate(date);
			List<OrderVO> orderVos = orders.stream().map(order ->modelMapper.map(order, OrderVO.class))
					.collect(Collectors.toList());
		return orderVos;
	}
	@Override
	public List<OrderVO> getOrdersByDateRange(LocalDateTime from, LocalDateTime to) {
		List<Order> orders = iRepository.getOrdersByDateRange(from, to);
		List<OrderVO> orderVos = orders.stream().map(order ->modelMapper.map(order, OrderVO.class)).collect(Collectors.toList());
		return orderVos;
	}

}
