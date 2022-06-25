package com.poc.opensource.customer.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.poc.opensource.customer.entity.Customer;
@Repository
public class CustomerRepositoryImpl implements ICustomerRepository{
	 @Autowired
	 private MongoTemplate mongoTemplate;
	 public List<Customer> getProductsByDate(LocalDateTime from, LocalDateTime to){
		 return  null;
	 }
}
