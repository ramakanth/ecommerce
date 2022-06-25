package com.poc.opensource.product.exception;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler({ ProductNotFoundException.class })
	public final ResponseEntity<Object>   handleProductNotFoundException(ProductNotFoundException ex,
			WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getStatusCode().name() ,ex.getStatusText(), request.getDescription(false),
				timeStamp);
		
		return new ResponseEntity<Object>(apiErrorMessage, HttpStatus.NOT_FOUND);
	}
	
	//@ExceptionHandler({ HttpClientErrorException.class })
	@ExceptionHandler({ ProductConflictException.class })
	public final ResponseEntity<Object> handleProductConflictException(ProductConflictException ex,
			WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(ex.getStatusCode().name() ,ex.getStatusText(), request.getDescription(false),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, ex.getStatusCode());
	}
	@ExceptionHandler({ Exception.class })
	public final ResponseEntity<Object> handleProductGeneralException(Exception ex, WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage("",ex.getLocalizedMessage(), request.getDescription(true),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Instant timeStamp = Instant.now();
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage("",ex.getLocalizedMessage(), request.getDescription(false),
				timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, status);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Instant timeStamp = Instant.now();
		List<String> collect = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
		
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage("",collect.toString(),"Validation is failed", timeStamp);
		return new ResponseEntity<Object>(apiErrorMessage, HttpStatus.BAD_REQUEST);
	}

}
