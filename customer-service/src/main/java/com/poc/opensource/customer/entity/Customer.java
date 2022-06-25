
package com.poc.opensource.customer.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document(collection="customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Transient
    public static final String CUSTOMER_ID_SEQ = "customer_sequence";
	@Id
	private long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String dayPhone;
	private String customerType;
	private String createdBy;
	private LocalDateTime createdDate;
	private String updatedBy;
	private LocalDateTime updatedDate;
}
