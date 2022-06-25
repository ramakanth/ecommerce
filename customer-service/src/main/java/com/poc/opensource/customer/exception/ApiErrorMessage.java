package com.poc.opensource.customer.exception;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiErrorMessage {
	private String errorMessage;
	private String requestingURI;
	private Instant timeStamp;

	public ApiErrorMessage(String messageError, String URI, Instant timeStamp) {
		this.errorMessage = messageError;
		this.requestingURI = URI;
		this.timeStamp = timeStamp;
	}
}
