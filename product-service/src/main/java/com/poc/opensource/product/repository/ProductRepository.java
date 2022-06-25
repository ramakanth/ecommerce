package com.poc.opensource.product.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.opensource.product.entity.Product;
@Repository
public interface ProductRepository extends MongoRepository<Product, Long>{
	
	@Query("{createdDate : {$lt : ?0, $gt : ?1}}")
	List<Product> findProductBySDateRange(LocalDateTime to, LocalDateTime from);
	
	List<Product> findByCreatedDate(LocalDate createdDate);
	Optional<Product> findByProductName(String productName);

}
