package com.poc.opensource.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillToAddressVO {
	private long addressId;
	private String addressLine1;
	private String addressLine2;
	private boolean isDefault;
	private String addressType;
	private String street;
	private String state;
	private String country;
	private String zipCode;


}
