package com.poc.opensource.order.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.poc.opensource.order.vo.CustomerVO;

import feign.FeignException;

public class CustomerServiceClientFallback implements CustomerServiceClient {
	Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Throwable cause;
    public CustomerServiceClientFallback(Throwable cause) {
    	this.cause = cause;
    }
	@Override
	public CustomerVO getCustomer(long id) {
		if(cause instanceof FeignException && ((FeignException)cause).status() == 400) {
			logger.error("404 error took place when customers was called with customerId: "
                    + id +  ". Error message: "
                    + cause.getLocalizedMessage());
        } else {
            logger.error("Other error took place: " + cause.getLocalizedMessage());
        }

        return new CustomerVO();
	}

}
