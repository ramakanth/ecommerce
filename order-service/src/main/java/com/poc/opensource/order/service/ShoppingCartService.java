package com.poc.opensource.order.service;

import com.poc.opensource.order.vo.OrderVO;

public interface ShoppingCartService {
	public OrderVO placeOrder(OrderVO orderVO);

}
