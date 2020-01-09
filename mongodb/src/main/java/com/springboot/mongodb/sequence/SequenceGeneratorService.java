package com.springboot.mongodb.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {
	
	@Autowired
	private MongoOperations mongoOperations;
	
	public int generateSequence(String seqName) {
		 
		 SequenceId seqId = mongoOperations.findAndModify(
		            new Query(Criteria.where("_id").is(seqName)),
		            new Update().inc("seq",1),
		            new FindAndModifyOptions().returnNew(true),
		            SequenceId.class);
	     return seqId.getSeq();
	}

}
