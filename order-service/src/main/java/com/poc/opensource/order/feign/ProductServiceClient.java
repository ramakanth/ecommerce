package com.poc.opensource.order.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.poc.opensource.order.vo.ProductRequestWrapper;
import com.poc.opensource.order.vo.ProductVO;

@FeignClient(name="PRODUCT-SERVICE", fallbackFactory=ProductServiceClientFallbackFactory.class)
public interface ProductServiceClient {
	@PostMapping("/products/selected")
	public List<ProductVO> getProducts(@RequestBody ProductRequestWrapper wrapper);
}
