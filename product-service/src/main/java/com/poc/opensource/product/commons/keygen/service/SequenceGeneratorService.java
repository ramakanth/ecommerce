package com.poc.opensource.product.commons.keygen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.poc.opensource.product.commons.keygen.entity.SequenceAutoGenEntity;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

@Service
public class SequenceGeneratorService {
	@Autowired
	private MongoOperations mongoOperations;

	public long generateSequence(String seqName) {

		SequenceAutoGenEntity counter = mongoOperations.findAndModify(query(where("_id").is(seqName)), new Update().inc("seq", 1),
				options().returnNew(true).upsert(true), SequenceAutoGenEntity.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;

	}
}
