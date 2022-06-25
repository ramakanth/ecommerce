package com.poc.opensource.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class CustomerNotFoundException extends HttpStatusCodeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomerNotFoundException() {
		super(HttpStatus.NOT_FOUND);
	}
	public CustomerNotFoundException(HttpStatus status, String message) {
		super(status, message);
	}
}
