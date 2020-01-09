package com.springboot.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.mongodb.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByuserId(String userId);
	User findByroll(int n);
	
	@Query("{ 'age' : {$gt: ?0, $lt: ?1 }}")
	List<User> findCustumByAgeBetween(int a, int b);
	
	List<User> findByAgeBetween(int a, int b);
	
	  @Query(value="{'name': ?0}", fields = "{'phone': 0}")
	    List<User> findCustomByName(String name);
	  
	  List<User> findByNameAndRoll(String name, int n);
	  
	  @Query("{name : {$ne : ?0}}")
	  List<User> findByNameNotEqual(String name);

}
