package com.poc.opensource.customer.exception;

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
	public CustomerNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
	public CustomerNotFoundException(HttpStatus status,String message) {
		super(status, message);
	}

}
