package com.poc.opensource.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.opensource.customer.commons.keygen.service.SequenceGeneratorService;
import com.poc.opensource.customer.entity.Address;
import com.poc.opensource.customer.repository.AddressRepository;
import com.poc.opensource.customer.vo.AddressVO;
@Service
public class AddressServiceimpl implements AddressService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private SequenceGeneratorService sequenceGen;
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public AddressVO createCustomerAddress(AddressVO addressVO) {
		Address address = modelMapper.map(addressVO, Address.class);
		address.setAddressId(sequenceGen.generateSequence(Address.ADDRESS_ID_SEQ));
		return modelMapper.map(addressRepository.save(address), AddressVO.class);
	}
	@Override
	public List<AddressVO> getAddressesByZipCode(String zipCode) {
		List<Address> addresses = addressRepository.findByZipCode(zipCode);
		List<AddressVO> addr = addresses.stream().map(add -> modelMapper.map(add, AddressVO.class))
				.collect(Collectors.toList());
		return addr;
	}
	@Override
	public List<AddressVO> getAddressesByCustomerId(long customerId) {
		List<Address> address = addressRepository.findByCustomerId(customerId);
		List<AddressVO> addresses = address.stream().map(add -> modelMapper.map(add, AddressVO.class))
				.collect(Collectors.toList());
		return addresses;
	}

}
