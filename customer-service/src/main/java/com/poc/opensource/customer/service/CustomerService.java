package com.poc.opensource.customer.service;

import java.time.LocalDateTime;
import java.util.List;

import com.poc.opensource.customer.vo.AddressVO;
import com.poc.opensource.customer.vo.CustomerVO;

public interface CustomerService {
  public CustomerVO createCustomer(CustomerVO customerVO);
  public List<CustomerVO> getAllCustomers();
  public CustomerVO getCustomerById(long customerId);
  public CustomerVO updateCustomer(CustomerVO customerVO);
  public void deleteCustomerById(long customerId);
  public List<CustomerVO> getAllCustomersByDate(LocalDateTime createdDate);
  public List<CustomerVO> getAllCustomersByDateRange(LocalDateTime to, LocalDateTime from);
  public int getAllCustomersCount();
  public AddressVO createCustomerAddress(AddressVO addressVO);
  public List<AddressVO> getCustomerAddress(long customerId);
  public List<CustomerVO> searchByFirstName(String firstName);
  public List<CustomerVO> searchByLastName(String lastName);
  public CustomerVO searchByEmail(String email);
  public CustomerVO searchByMobile(String mobile);
  
}
