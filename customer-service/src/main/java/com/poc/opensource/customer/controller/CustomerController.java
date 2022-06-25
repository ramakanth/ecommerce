package com.poc.opensource.customer.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.poc.opensource.customer.service.CustomerService;
import com.poc.opensource.customer.validator.RequestValidator;
import com.poc.opensource.customer.vo.CustomerVO;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private RequestValidator requestValidator;
	
	@PostMapping("/customers")
	public ResponseEntity<CustomerVO> createCustomer(@Valid @RequestBody CustomerVO customerVO){
		CustomerVO dbCustomer = customerService.createCustomer(customerVO);
		return new ResponseEntity<>(dbCustomer,HttpStatus.CREATED);
	}
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerVO> getCustomerById(@PathVariable("id") long id){
		CustomerVO customer = customerService.getCustomerById(id);
		return new ResponseEntity<CustomerVO>(customer,HttpStatus.OK);
	}
	@GetMapping("/customers/firstname/{firstName}")
	public ResponseEntity<List<CustomerVO>> searchByFirstName(@PathVariable("firstName") String firstName){
		requestValidator.validateFirstName(firstName);
		List<CustomerVO> customers = customerService.searchByFirstName(firstName);
		return new ResponseEntity<List<CustomerVO>>(customers,HttpStatus.OK);
	}
	@GetMapping("/customers/lastname/{lastName}")
	public ResponseEntity<List<CustomerVO>> searchByLastName(@PathVariable("lastName") String lastName){
		requestValidator.validateLastName(lastName);
		List<CustomerVO> customers = customerService.searchByLastName(lastName);
		return new ResponseEntity<List<CustomerVO>>(customers,HttpStatus.OK);
	}
	@GetMapping("/customers/email/{email}")
	public ResponseEntity<CustomerVO> searchByEmail(@PathVariable("email") String email){
		requestValidator.validateEmail(email);
		CustomerVO customer = customerService.searchByEmail(email);
		return new ResponseEntity<CustomerVO>(customer,HttpStatus.OK);
	}
	@GetMapping("/customers/mobile/{mobile}")
	public ResponseEntity<CustomerVO> searchByMobile(@PathVariable("mobile") String mobile){
		requestValidator.validateMobile(mobile);
		CustomerVO customer = customerService.searchByMobile(mobile);
		return new ResponseEntity<CustomerVO>(customer,HttpStatus.OK);
	}
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerVO>> getAllCustomers(){
		List<CustomerVO> customers = customerService.getAllCustomers();
		return new  ResponseEntity<List<CustomerVO>>(customers,HttpStatus.OK);
	}
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable("id") long id){
		customerService.deleteCustomerById(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	@PutMapping("/customers")
	public ResponseEntity<CustomerVO> updateCustomer(@Valid @RequestBody CustomerVO customerVO){
		Optional<CustomerVO> condition = Optional.ofNullable(customerVO);
		if(!condition.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Customer is null"); 
		}
		Optional<Long> conditionId = Optional.ofNullable(customerVO.getCustomerId());
		if(conditionId.isPresent() && conditionId.get() <= 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "CustomerId is null"); 
		}
		CustomerVO updatedCustomerVO = customerService.updateCustomer(customerVO);
		return new ResponseEntity<CustomerVO>(updatedCustomerVO,HttpStatus.OK);
	}
	

}
