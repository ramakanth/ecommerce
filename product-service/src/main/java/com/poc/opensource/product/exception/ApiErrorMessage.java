package com.poc.opensource.product.exception;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorMessage {
	private String statusCode;
	private String message;
	private String uri;
	private Instant timeStamp;
}
