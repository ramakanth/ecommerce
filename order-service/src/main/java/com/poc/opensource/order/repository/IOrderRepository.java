package com.poc.opensource.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.poc.opensource.order.entity.Order;

public interface IOrderRepository {
	public List<Order> getOrdersByDate(LocalDateTime date);
	public List<Order> getOrdersByDateRange(LocalDateTime from, LocalDateTime to);
	

}
