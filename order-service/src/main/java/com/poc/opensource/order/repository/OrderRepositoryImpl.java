package com.poc.opensource.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.poc.opensource.order.entity.Order;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
public class OrderRepositoryImpl implements IOrderRepository{
	 public static final String ORDER_DATE = "orderDate";
	 @Autowired
	 private MongoTemplate mongoTemplate;

	@Override
	public List<Order> getOrdersByDate(LocalDateTime date) {
		LocalDateTime endOfday = date.plusDays(1).minusSeconds(1);
		final Query query = new Query();
        if (date != null) {
            query.addCriteria(Criteria.where(ORDER_DATE).gte(date).lte(endOfday));
        }

        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;

	}

	@Override
	public List<Order> getOrdersByDateRange(LocalDateTime from, LocalDateTime to) {
		final Query query = new Query();
        if (from != null & to !=null) {
            query.addCriteria(Criteria.where(ORDER_DATE).gte(from).lte(to));
        }
        List<Order> orders = mongoTemplate.find(query, Order.class);
		return orders;
	}
	 
	
}
