package com.poc.opensource.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.poc.opensource.customer.service.AddressService;
import com.poc.opensource.customer.service.CustomerService;
import com.poc.opensource.customer.vo.AddressVO;
import com.poc.opensource.customer.vo.CustomerVO;
import com.poc.opensource.customer.vo.ProfileVO;


@RestController
public class ProfileController {
	@Autowired
	private CustomerService custService;
	@Autowired
	private AddressService addService;
	
	@GetMapping("/profile/{customerId}")
	public ResponseEntity<ProfileVO> getProfileById(@PathVariable("customerId") long customerId ){
		if(customerId <= 0) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid customerId");
		}
		CustomerVO customer = custService.getCustomerById(customerId);
		List<AddressVO> addresses = addService.getAddressesByCustomerId(customerId);
		ProfileVO profile = new ProfileVO(customer, addresses);
		return new ResponseEntity<ProfileVO>(profile, HttpStatus.OK);
	}
	@GetMapping("/profile/email/{email}")
	public ResponseEntity<ProfileVO> getProfileById(@PathVariable("email") String email ){
		if(StringUtils.isEmpty(email)) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid email");
		}
		CustomerVO customer = custService.searchByEmail(email);
		List<AddressVO> addresses = addService.getAddressesByCustomerId(customer.getCustomerId());
		ProfileVO profile = new ProfileVO(customer, addresses);
		return new ResponseEntity<ProfileVO>(profile, HttpStatus.OK);
	}

}
