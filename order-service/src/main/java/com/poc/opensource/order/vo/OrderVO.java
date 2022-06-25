package com.poc.opensource.order.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
	private long orderId;
	private long customerId;

	private LocalDateTime orderDate;
	private LocalDateTime shippingDate;
	private String shippingMethod;
	private String orderStatus;
	private double orderTotal;
	private double taxAmt;
	private double itemsTotalPrice;

	private List<LineItemVO> lineItems;
	private AddressVO shipAddress;
	private AddressVO billAddress;
	private PaymentVO payment;

}
