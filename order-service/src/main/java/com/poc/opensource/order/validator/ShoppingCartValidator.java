package com.poc.opensource.order.validator;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.poc.opensource.order.vo.ShoppingCartVO;
import com.poc.opensource.order.exception.CustomerNotFoundException;
import com.poc.opensource.order.exception.PaymentException;
import com.poc.opensource.order.exception.ProductNotFoundException;
@Component
public class ShoppingCartValidator {
	public void validateInput(ShoppingCartVO hoppingCartVO) {
		Optional<ShoppingCartVO> ofNullable = Optional.ofNullable(hoppingCartVO);
		if(!ofNullable.isPresent()) {
			throw new ProductNotFoundException(HttpStatus.BAD_REQUEST, "Shopping Cart is empty !");
		}else if(!Optional.ofNullable(hoppingCartVO.getCustomerId()).isPresent() || hoppingCartVO.getCustomerId() <= 0) {
			throw new CustomerNotFoundException(HttpStatus.BAD_REQUEST, "Customer not registered...");
		}else if(!Optional.ofNullable(hoppingCartVO.getProductId()).isPresent() || hoppingCartVO.getCustomerId() <= 0) {
			throw new ProductNotFoundException(HttpStatus.BAD_REQUEST, "Invalid Product Id...");
		}else if(!Optional.ofNullable(hoppingCartVO.getPayment()).isPresent()) {
			throw new PaymentException(HttpStatus.BAD_REQUEST, "Invalid payment Id...");
		}
	  }
}
