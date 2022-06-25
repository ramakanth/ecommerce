package com.poc.opensource.customer.entity;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="address")
public class Address {
	@Transient
    public static final String ADDRESS_ID_SEQ = "address_sequence";
	@Id
	private long addressId;
	private long customerId;
	private String addressLine1;
	private String addressLine2;
	private String isDefault;
	private String addressType;
	private String street;
	private String state;
	private String country;
	private String zipCode;
	private String createdBy;
	private LocalDateTime createdDate;
	private String updatedBy;
	private LocalDateTime updatedDate;
	

}
