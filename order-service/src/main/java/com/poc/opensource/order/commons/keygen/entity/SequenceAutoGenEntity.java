package com.poc.opensource.order.commons.keygen.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "database_sequences")
public class SequenceAutoGenEntity {
	@Id
	private String id;
	private long seq;
}
