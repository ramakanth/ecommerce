package com.poc.opensource;

import java.util.ArrayList;
import java.util.List;

import com.poc.opensource.customer.vo.AddressVO;

public class AddressIntegrationTests {
	
	public List<AddressVO> buildAddressVO() {
		AddressVO billingAddr = new AddressVO(0, 3, "Near KIms", "Kothaguda", 
				"true", "B", "kondapur", "Telanagana", "India", "500085");
		AddressVO shippingAddr = new AddressVO(0, 3, "Near KIms", "Kothaguda", 
				"true", "B", "kondapur", "Telanagana", "India", "500085");
		
		List<AddressVO> addresses = new ArrayList<>();
		addresses.add(billingAddr);
		addresses.add(shippingAddr);
		return addresses;
	}

}
