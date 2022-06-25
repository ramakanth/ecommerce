package com.poc.opensource.customer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import com.poc.opensource.customer.commons.keygen.service.SequenceGeneratorService;
import com.poc.opensource.customer.entity.Address;
import com.poc.opensource.customer.entity.Customer;
import com.poc.opensource.customer.exception.CustomerNotFoundException;
import com.poc.opensource.customer.repository.AddressRepository;
import com.poc.opensource.customer.repository.CustomerRepository;
import com.poc.opensource.customer.vo.AddressVO;
import com.poc.opensource.customer.vo.CustomerVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Service
public class CustomerServiceImpl implements CustomerService {
	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerRepository repository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private SequenceGeneratorService sequenceGen;
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public CustomerVO createCustomer(CustomerVO customerVO) {
		Customer customer = modelMapper.map(customerVO, Customer.class);
		customer.setCustomerId(sequenceGen.generateSequence(Customer.CUSTOMER_ID_SEQ));
		customer.setCreatedBy("RAMA");
		customer.setCreatedDate(LocalDateTime.now());
		return modelMapper.map(repository.save(customer), CustomerVO.class);
	}

	@Override
	public List<CustomerVO> getAllCustomers() {
		List<Customer> customers = repository.findAll();
		if(customers.size() <= 0) {
			logger.info("Size of customers : "+customers.size());
			throw new CustomerNotFoundException(HttpStatus.NO_CONTENT,"No customer is found");
		}
		List<CustomerVO> customerVos = customers.stream().map(customer -> modelMapper.map(customer, CustomerVO.class))
				.collect(Collectors.toList());
		logger.debug("Size of customers :"+customerVos.size());
		return customerVos;
	}

	@Override
	public CustomerVO getCustomerById(long customerId) {
		Customer customer = repository.findByCustomerId(customerId);
		if(customer == null) {
			throw new CustomerNotFoundException(HttpStatus.NO_CONTENT,
					"Customer with customer ID :  (" + customerId + ") is not found!");
		}
		CustomerVO customerVO = modelMapper.map(customer, CustomerVO.class);
		return customerVO;
	}
	@Override
	public List<CustomerVO> searchByFirstName(String firstName){
		List<Customer> customers = repository.findByFirstName(firstName);
		if(customers.size() <= 0) {
			logger.info("Size of customers : "+customers.size());
			throw new CustomerNotFoundException(HttpStatus.NO_CONTENT,"No customer is found");
		}
		List<CustomerVO> customerVos = customers.stream().map(customer -> modelMapper.map(customer, CustomerVO.class))
				.collect(Collectors.toList());
		logger.debug("Size of customers :"+customerVos.size());
		return customerVos;
	}
	@Override
	public List<CustomerVO> searchByLastName(String lastName){
		List<Customer> customers = repository.findByLastName(lastName);
		if(customers.size() <= 0) {
			logger.info("Size of customers : "+customers.size());
			throw new CustomerNotFoundException(HttpStatus.NO_CONTENT,"No customer is found");
		}
		List<CustomerVO> customerVos = customers.stream().map(customer -> modelMapper.map(customer, CustomerVO.class))
				.collect(Collectors.toList());
		logger.debug("Size of customers :"+customerVos.size());
		return customerVos;
	}
	@Override
	public CustomerVO searchByEmail(String email){
		Customer customer = repository.findByEmail(email);
		if(customer == null) {
			throw new CustomerNotFoundException(HttpStatus.NO_CONTENT,
					"Customer with email :  (" + email + ") is not found!");
		}
		CustomerVO customerVO = modelMapper.map(customer, CustomerVO.class);
		return customerVO;
	}
	@Override
	public CustomerVO searchByMobile(String mobile){
		Customer customer = repository.findByDayPhone(mobile);
		if(customer == null) {
			throw new CustomerNotFoundException(HttpStatus.NO_CONTENT,
					"Customer with mobile :  (" + mobile + ") is not found!");
		}
		CustomerVO customerVO = modelMapper.map(customer, CustomerVO.class);
		return customerVO;
	}

	@Override
	public CustomerVO updateCustomer(CustomerVO customerVO) {
		Optional<Customer> customerDb = repository.findById(customerVO.getCustomerId());
		if (!customerDb.isPresent()) {
			throw new CustomerNotFoundException(
					"Customer with customer ID :  (" + customerVO.getCustomerId() + ") not found!");
		}
		Customer customer = customerDb.get();
		BeanUtils.copyProperties(customerVO, customer);
		return modelMapper.map(repository.save(customer), CustomerVO.class);
	}

	@Override
	public void deleteCustomerById(long customerId) {
		Optional<Customer> product = repository.findById(customerId);
		if (!product.isPresent()) {
			throw new CustomerNotFoundException("Customer with customer ID :  (" + customerId + ") not found!");
		}
		repository.delete(product.get());

	}

	@Override
	public List<CustomerVO> getAllCustomersByDate(LocalDateTime createdDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerVO> getAllCustomersByDateRange(LocalDateTime to, LocalDateTime from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllCustomersCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AddressVO createCustomerAddress(AddressVO addressVO) {
		Address address = modelMapper.map(addressVO, Address.class);
		address.setAddressId(sequenceGen.generateSequence(Address.ADDRESS_ID_SEQ));
		return modelMapper.map(addressRepository.save(address), AddressVO.class);
	}
	@Override
	public List<AddressVO> getCustomerAddress(long customerId) {
		List<Address> address = addressRepository.findByCustomerId(customerId);
		List<AddressVO> addresses = address.stream().map(add -> modelMapper.map(add, AddressVO.class))
				.collect(Collectors.toList());
		return addresses;
	}
	
	

}
