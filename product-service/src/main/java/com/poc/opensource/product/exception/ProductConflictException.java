package com.poc.opensource.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ProductConflictException extends HttpStatusCodeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ProductConflictException() {
		super(HttpStatus.CONFLICT);
	}
	public ProductConflictException(String message) {
		super(HttpStatus.CONFLICT, message);
	}

	
}
