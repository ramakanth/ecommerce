package com.poc.opensource.product.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.poc.opensource.product.entity.Product;

public class ProductRepositoryImpl implements IProductRepository{
	 @Autowired
	 private MongoTemplate mongoTemplate;
	 
	 public List<Product> getProductsByDate(LocalDate date){
		 Query query = new Query();
		 query.addCriteria(Criteria.where("createdDate").is(date));
		 return  mongoTemplate.find(query, Product.class);
	 }
}
