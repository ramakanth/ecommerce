package com.poc.opensource.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class PaymentException extends HttpStatusCodeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PaymentException() {
		super(HttpStatus.NOT_FOUND);
	}
	public PaymentException(HttpStatus status, String message) {
		super(status, message);
	}
}
