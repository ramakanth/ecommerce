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
public class AddressVO {
	private long addressId;
	private String addressLine1;
	private String addressLine2;
	private String isDefault;
	private String addressType;
	private String street;
	private String state;
	private String country;
	private String zipCode;
	

}
