package com.poc.opensource;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.opensource.customer.controller.CustomerController;
import com.poc.opensource.customer.service.CustomerService;
import com.poc.opensource.customer.vo.AddressVO;
import com.poc.opensource.customer.vo.CustomerVO;

@WebMvcTest(CustomerController.class)
@ActiveProfiles("test")
class CustomerServiceComponentTests {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private CustomerService customerService;

	@Test
	public void testCreateCustomer() throws Exception {
		AddressVO billing = new AddressVO();
		AddressVO shipping = new AddressVO();
		List<AddressVO> addresses = new ArrayList<>();
		addresses.add(billing);
		addresses.add(shipping);
		
		CustomerVO customer = new CustomerVO(200, "Aadhya", "Dhane", "aadhya@nisum.com", "9000501368", "REGULAR");
		Mockito.when(customerService.createCustomer(Mockito.any(CustomerVO.class))).thenReturn(customer);
		String customerJSON = mapper.writeValueAsString(customer);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/customers")
				.accept(MediaType.APPLICATION_JSON)
				.content(customerJSON).contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isCreated());
		resultActions.andExpect(jsonPath("$.customerId").exists());
		resultActions.andExpect(jsonPath("$.email", is("aadhya@nisum.com")));
		resultActions.andExpect(jsonPath("$.firstName").value("Aadhya"));
	}
	@Test
	public void testCreateCustomer_400() throws Exception {
		AddressVO billing = new AddressVO();
		AddressVO shipping = new AddressVO();
		List<AddressVO> addresses = new ArrayList<>();
		addresses.add(billing);
		addresses.add(shipping);
		CustomerVO customer = new CustomerVO(200, "Aadhya", "Dhane", null, "9000501368", "REGULAR");
		Mockito.when(customerService.createCustomer(Mockito.any(CustomerVO.class))).thenReturn(customer);
		String customerJSON = mapper.writeValueAsString(customer);
		mockMvc.perform(MockMvcRequestBuilders.post("/customers")
				.accept(MediaType.APPLICATION_JSON)
				.content(customerJSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	@Test
	public void testGetCustomer( ) throws Exception{
		AddressVO billing = new AddressVO();
		AddressVO shipping = new AddressVO();
		List<AddressVO> addresses = new ArrayList<>();
		addresses.add(billing);
		addresses.add(shipping);
		CustomerVO customer = new CustomerVO(200, "Aadhya", "Dhane", "aadhya@nisum.com", "9000501368", "REGULAR");
		Mockito.when(customerService.getCustomerById(customer.getCustomerId())).thenReturn(customer);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/customers/{id}",200)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andDo(print())
		        .andExpect(jsonPath("$.customerId").value(200));
	}
	@Test
	public void testGetAllCustomers() throws Exception {
		AddressVO billing = new AddressVO();
		AddressVO shipping = new AddressVO();
		List<AddressVO> addresses = new ArrayList<>();
		addresses.add(billing);
		addresses.add(shipping);
		CustomerVO customer1 = new CustomerVO(100, "Ramakanth", "Dhane", "rdhane@nisum.com", "9000501368", "REGULAR");
		CustomerVO customer2 = new CustomerVO(200, "Aadhya", "Dhane", "aadhya@nisum.com", "9000501368", "REGULAR");
		List<CustomerVO> customers = new ArrayList<>();
		customers.add(customer1);
		customers.add(customer2);
		Mockito.when(customerService.getAllCustomers()).thenReturn(customers);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/customers")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].customerId", is(100)))
		.andExpect(jsonPath("$[1].customerId",is(200)));
		verify(customerService,times(1)).getAllCustomers();
		
	}
	@Test
	public void testDeleteCustomer() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/customers/{id}", 100)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent())
		.andDo(print());
		verify(customerService,times(1)).deleteCustomerById(100);
	}
	@Test
	public void testUpdateCustomer() throws Exception{
		AddressVO billing = new AddressVO();
		AddressVO shipping = new AddressVO();
		List<AddressVO> addresses = new ArrayList<>();
		addresses.add(billing);
		addresses.add(shipping);
		CustomerVO customerVO = new CustomerVO(200, "Ramakanth", "Dhane", "rdhane@nisum.com", "9000501368", "REGULAR");
		Mockito.when(customerService.updateCustomer(customerVO)).thenReturn(customerVO);
		mockMvc.perform(MockMvcRequestBuilders.put("/customers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(customerVO))
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customerId").value(200))
		.andExpect(jsonPath("$.email").value("rdhane@nisum.com"));
	}
	@Test
	public void testUpdateCustomer_400() throws Exception{
		AddressVO billing = new AddressVO();
		AddressVO shipping = new AddressVO();
		List<AddressVO> addresses = new ArrayList<>();
		addresses.add(billing);
		addresses.add(shipping);
		CustomerVO customerVO = new CustomerVO(0, "Aadhya", "Dhane", "rdhane@gmail.com", "9000501368", "REGULAR");
		Mockito.when(customerService.updateCustomer(customerVO)).thenReturn(customerVO);
		mockMvc.perform(MockMvcRequestBuilders.put("/customers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(customerVO))
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
}
