package com.poc.opensource.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.poc.opensource.order.vo.CustomerVO;

@FeignClient(name="CUSTOMER-SERVICE", fallbackFactory=CustomerServiceClientFallbackFactory.class)
public interface CustomerServiceClient {
	@GetMapping("/customer/{id}")
	public CustomerVO getCustomer(@PathVariable("id") long id);
}
