package com.poc.opensource.order.feign;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import com.poc.opensource.order.vo.ProductRequestWrapper;
import com.poc.opensource.order.vo.ProductVO;

import feign.FeignException;

public class ProductServiceClientFallback implements ProductServiceClient {
	Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Throwable cause;
    public ProductServiceClientFallback(Throwable cause) {
    	this.cause = cause;
    }
	@Override
	public List<ProductVO> getProducts(@RequestBody ProductRequestWrapper wrapper) {
		if(cause instanceof FeignException && ((FeignException)cause).status() == 400) {
			logger.error("404 error took place when products was called with productIds: "
                    + wrapper.getClass() +  ". Error message: "
                    + cause.getLocalizedMessage());
        } else {
            logger.error("Other error took place: " + cause.getLocalizedMessage());
        }

        return new ArrayList<>();
	}

}
