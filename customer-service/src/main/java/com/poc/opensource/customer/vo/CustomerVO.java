package com.poc.opensource.customer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO {
	private long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String dayPhone;
	private String customerType;
	
	

}
