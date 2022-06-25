package com.poc.opensource.order.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.opensource.order.entity.Order;
@Repository
public interface OrderRepository extends MongoRepository<Order, Long>{
	
	@Query("{createdDate : {$lt : ?0, $gt : ?1}}")
	List<Order> findOrderBySDateRange(LocalDateTime to, LocalDateTime from);

}
