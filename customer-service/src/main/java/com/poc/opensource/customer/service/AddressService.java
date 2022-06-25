package com.poc.opensource.customer.service;

import java.util.List;

import com.poc.opensource.customer.vo.AddressVO;

public interface AddressService {
	public List<AddressVO> getAddressesByCustomerId(long customerId);
    public List<AddressVO> getAddressesByZipCode(String zipCode);
	AddressVO createCustomerAddress(AddressVO addressVO);
}
