package com.poc.opensource.order.vo;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CustomerVO {
	private long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String dayPhone;
	private String customerType;
	private List<AddressVO> addresses;
	

}
