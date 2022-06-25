package com.poc.opensource.product.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
	private Long productId;
	@NotNull(message = "First name is a required field")
	@Size(min = 1, max = 30, message = "First name must be minum 3 and max 30 characters")
	private String productName;
	@NotNull(message = "Description is a required field")
	@Size(min = 1, max = 30, message = "First name must be minum 3 and max 30 characters")
	private String description;
	@Positive
	private double price;
}
