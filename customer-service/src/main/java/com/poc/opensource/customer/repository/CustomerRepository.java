package com.poc.opensource.customer.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.opensource.customer.entity.Customer;
@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long>{
	
	Customer findByCustomerId(long customerId);
	List<Customer> findByFirstName(String firstName);
	List<Customer> findByLastName(String lastName);
	Customer findByEmail(String email);
	Customer findByDayPhone(String dayPhone);
	
	@Query("{createdDate : {$lt : ?0, $gt : ?1}}")
	List<Customer> findCustomerBySDateRange(LocalDateTime to, LocalDateTime from);

}
