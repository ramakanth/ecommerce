package com.poc.opensource.order.entity;


import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItem {
	@Transient
    public static final String LINE_ITEM_ID_SEQ = "line_item_seq";
	 private long lineItemId;
	 private long productId;
	 private String productName;
	 private String description;
	 private double price;
}
