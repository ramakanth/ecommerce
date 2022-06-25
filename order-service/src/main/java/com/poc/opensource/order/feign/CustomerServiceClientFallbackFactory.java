package com.poc.opensource.order.feign;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class CustomerServiceClientFallbackFactory implements FallbackFactory<CustomerServiceClient>{
	@Override
	public CustomerServiceClient create(Throwable cause) {
		return new CustomerServiceClientFallback(cause);
	}

}
