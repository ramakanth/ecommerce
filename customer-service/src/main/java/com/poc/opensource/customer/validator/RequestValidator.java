package com.poc.opensource.customer.validator;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
@Component
public class RequestValidator {
	public void validateEmail(String email) {
		if (!Optional.ofNullable(email).isPresent()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Email is empty!..");
		}
	}

	public void validateFirstName(String firstName) {
		if (!Optional.ofNullable(firstName).isPresent()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "First name is empty!..");
		}
	}

	public void validateLastName(String lastName) {
		if (!Optional.ofNullable(lastName).isPresent()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Last Name is empty!..");
		}
	}
	public void validateMobile(String lastName) {
		if (!Optional.ofNullable(lastName).isPresent()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Mobile number is empty!..");
		}
	}

}
