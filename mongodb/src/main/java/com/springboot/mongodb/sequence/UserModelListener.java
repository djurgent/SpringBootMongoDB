package com.springboot.mongodb.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.springboot.mongodb.model.User;

@Component
@PropertySource("classpath:custum.properties")
public class UserModelListener extends AbstractMongoEventListener<User>{
	@Autowired
    Environment env;
	
	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	@Override
	public void onBeforeConvert(BeforeConvertEvent<User> event) {
	    if (event.getSource().getRoll() < 1) {
	        event.getSource().setRoll(sequenceGenerator.generateSequence(env.getProperty("USER_SEQUENCE_NAME")));
	    }
	}

}
