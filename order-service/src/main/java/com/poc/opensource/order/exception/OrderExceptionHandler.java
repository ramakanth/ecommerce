package com.poc.opensource.order.exception;

import java.time.Instant;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler({ OrderNotFoundException.class })
	public final ResponseEntity<Object> handleProductNotFoundException(OrderNotFoundException ex,
			WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage(), request.getDescription(false),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, ex.getStatusCode());
	}
	@ExceptionHandler({ PaymentException.class })
	public final ResponseEntity<Object> handlePaymentException(PaymentException ex,
			WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage(), request.getDescription(false),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, ex.getStatusCode());
	}
	@ExceptionHandler({ CustomerNotFoundException.class })
	public final ResponseEntity<Object> handleCustomerException(CustomerNotFoundException ex,
			WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage(), request.getDescription(false),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, ex.getStatusCode());
	}
	@ExceptionHandler({ ProductNotFoundException.class })
	public final ResponseEntity<Object> handleProductException(ProductNotFoundException ex,
			WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getMessage(), request.getDescription(false),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, ex.getStatusCode());
	}
	
	@ExceptionHandler({ HttpClientErrorException.class })
	public final ResponseEntity<Object> handleProductConflictException(HttpClientErrorException ex,
			WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getLocalizedMessage(), request.getDescription(false),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, ex.getStatusCode());
	}
	@ExceptionHandler({ Exception.class })
	public final ResponseEntity<Object> handleProductGeneralException(Exception ex, WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getLocalizedMessage(), request.getDescription(true),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getLocalizedMessage(), request.getDescription(false),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, status);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getLocalizedMessage(), request.getDescription(false),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, status);
	}

}
