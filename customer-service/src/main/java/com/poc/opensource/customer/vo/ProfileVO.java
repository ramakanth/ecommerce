package com.poc.opensource.customer.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileVO {
	private CustomerVO customer;
	private List<AddressVO> addresses;

}
