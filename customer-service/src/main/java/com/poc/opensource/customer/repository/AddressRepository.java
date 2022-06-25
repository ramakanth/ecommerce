package com.poc.opensource.customer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.poc.opensource.customer.entity.Address;
@Repository
public interface AddressRepository extends MongoRepository<Address, Long>{
	
	 List<Address> findByCustomerId(long customerId);
	 List<Address> findByZipCode(String zipCode);

}
