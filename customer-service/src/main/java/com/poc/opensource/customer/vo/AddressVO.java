package com.poc.opensource.customer.vo;


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
	private long customerId;
	private String addressLine1;
	private String addressLine2;
	private String isDefault;
	private String addressType;
	private String street;
	private String state;
	private String country;
	private String zipCode;

}
