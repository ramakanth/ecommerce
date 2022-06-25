package com.poc.opensource.order.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartVO {
	private Long customerId;
	private List<Long> productId;
	private PaymentVO payment;
	

}
