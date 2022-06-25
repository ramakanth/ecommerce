package com.poc.opensource.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItemVO {
	 private long lineItemId;
	 private long productId;
	 private String productName;
	 private String description;
	 private double price;
}
