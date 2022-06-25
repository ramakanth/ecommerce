package com.poc.opensource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.poc.opensource.customer.entity.Customer;
import com.poc.opensource.customer.repository.CustomerRepository;
import com.poc.opensource.customer.vo.CustomerVO;
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = CustomerServiceApplication.class)
@ActiveProfiles("integration")
class CustomerIntegrationTests {
	final private static int PORT = 8100;
    final private static String HOST = "http://localhost:";

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private CustomerRepository repository;
	
	@Test
	public void testCreateCustomer() {
		HttpEntity<CustomerVO> customer = new HttpEntity<CustomerVO>(buildCustomer());
		ResponseEntity<CustomerVO> customerOut = this.restTemplate.exchange(HOST+PORT+"/customers", HttpMethod.POST, customer, CustomerVO.class);
		assertEquals(HttpStatus.CREATED, customerOut.getStatusCode());
		assertNotNull(customerOut.getBody());
		assertNotNull(customerOut.getBody().getCustomerId());
		assertNotNull(customerOut.getBody().getEmail());
		Customer cust = repository.findByCustomerId(customerOut.getBody().getCustomerId());
		assertNotNull(cust.getCustomerId());
	}
	@Test
	public void testGetCustomer() {
		 ResponseEntity<List<Customer>> responseEntity = this.restTemplate
	                .exchange(HOST + PORT + "/customers/rdhane10@nisum.com", HttpMethod.GET, null,
	                          new ParameterizedTypeReference<List<Customer>>() {
	                          });

	        List<Customer> customerList = responseEntity.getBody();

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertTrue(customerList.size() > 0);
	}
	@Test
	public void testGetAllCustomers() {
		 ResponseEntity<List<Customer>> responseEntity = this.restTemplate
	                .exchange(HOST + PORT + "/customers", HttpMethod.GET, null,
	                          new ParameterizedTypeReference<List<Customer>>() {
	                          });

	        List<Customer> customerList = responseEntity.getBody();

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertTrue(customerList.size() > 0);
	}
	@Test
	public void testDeleteCustomer() {
		Customer op = repository.findByEmail("rdhane10@nisum.com");
		restTemplate.exchange(HOST+PORT+"/customers/"+op.getCustomerId(), HttpMethod.DELETE, null, Void.class);
	}
	public CustomerVO buildCustomer() {
		CustomerVO customer = new CustomerVO(0, "ramakanth", "dhane", "rdhane10@nisum.com",
				"9000501368", "REGULAR");
		return customer;
	}
	
	
}
