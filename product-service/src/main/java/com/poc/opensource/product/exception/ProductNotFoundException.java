package com.poc.opensource.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ProductNotFoundException extends HttpStatusCodeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException() {
		super(HttpStatus.NOT_FOUND);
	}
	public ProductNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

}
