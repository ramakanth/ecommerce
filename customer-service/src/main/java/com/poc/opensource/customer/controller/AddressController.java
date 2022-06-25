package com.poc.opensource.customer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.poc.opensource.customer.service.AddressService;
import com.poc.opensource.customer.service.CustomerService;
import com.poc.opensource.customer.vo.AddressVO;

@RestController
public class AddressController {
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/address")
	public ResponseEntity<AddressVO> createAddress(@RequestBody AddressVO addressVO){
		Optional<String> addressType = Optional.ofNullable(addressVO.getAddressType());
		if(!addressType.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "AddressType is blank");
		}
		customerService.getCustomerById(addressVO.getCustomerId());
		AddressVO dbAddress = addressService.createCustomerAddress(addressVO);
		return new ResponseEntity<>(dbAddress,HttpStatus.CREATED);
	}
	@GetMapping("/address/customer/{id}")
	public ResponseEntity<List<AddressVO>> getAddressCustomerById(@PathVariable("id") long id){
		List<AddressVO> address = addressService.getAddressesByCustomerId(id);
		return new ResponseEntity<List<AddressVO>>(address,HttpStatus.OK);
	}
	@GetMapping("/address/zipcode/{zipCode}")
	public ResponseEntity<List<AddressVO>> getAddressByZipCode(@PathVariable("zipCode") String zipCode){
		List<AddressVO> address = addressService.getAddressesByZipCode(zipCode);
		return new ResponseEntity<List<AddressVO>>(address,HttpStatus.OK);
	}

}
