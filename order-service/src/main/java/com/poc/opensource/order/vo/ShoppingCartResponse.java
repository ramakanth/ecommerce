package com.poc.opensource.order.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartResponse {
	private long orderId;
	private LocalDateTime shippingDate;
	private String orderStatus;
	private double orderTotal;
}
