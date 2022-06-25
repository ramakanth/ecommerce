package com.poc.opensource.product.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="products")
public class Product {
	@Transient
    public static final String PRODUCT_ID_SEQ = "product_sequence";
	@Id
	private Long productId;
	private String productName;
	private String description;
	private double price;
	private String createdBy;
	private LocalDateTime createdDate;
	private String updatedBy;
	private LocalDateTime updatedDate;

}
