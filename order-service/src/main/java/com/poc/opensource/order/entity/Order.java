package com.poc.opensource.order.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.poc.opensource.order.vo.AddressVO;
import com.poc.opensource.order.vo.LineItemVO;
import com.poc.opensource.order.vo.PaymentVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Transient
    public static final String ORDER_ID_SEQ = "order_sequence";
	@Id
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
