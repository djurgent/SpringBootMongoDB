package com.springboot.mongodb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.mongodb.model.User;
import com.springboot.mongodb.repository.UserRepository;
import com.springboot.mongodb.sequence.SequenceGeneratorService;


@RestController
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "Welcome to the MongoDbApp";
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		logger.info("Getting all users.");
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		logger.info("Getting user with ID: {}.", userId);
		return userRepository.findByuserId(userId);
	}
	
	@RequestMapping(value = "/getUserByRoll/{rollNo}", method = RequestMethod.GET)
	public User getUserByRoll(@PathVariable int rollNo) {
		logger.info("Getting user with ID: {}.", rollNo);
		return userRepository.findByroll(rollNo);
	}
	
	@RequestMapping(value = "/editUser/{userId}", method = RequestMethod.PUT)
	public User editUser(@RequestBody User user) {
		logger.info("Editing user."+user);
		return userRepository.save(user);
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		logger.info("Saving user."+user);
		System.out.println("Inside Creating a new User");
		return userRepository.save(user);
	}
	
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable String userId) {
		logger.info("Editing user."+userId);
		userRepository.deleteById(userId);
		return;
	}
	
	@RequestMapping(value = "/findCustumByAgeBetween/{a}/{b}", method = RequestMethod.GET)
	public List<User> findCustumByAgeBetween(@PathVariable int a, @PathVariable int b) {
		logger.info("Getting user with ID: {}.%%%%%%%%%");
		return userRepository.findCustumByAgeBetween(a,b);
	}
	
	@RequestMapping(value = "/findByAgeBetween/{a}/{b}", method = RequestMethod.GET)
	public List<User> findByAgeBetween(@PathVariable int a, @PathVariable int b) {
		logger.info("Getting user with ID: {}.%%%%%%%%%");
		return userRepository.findByAgeBetween(a,b);
	}
	
	@RequestMapping(value = "/findCustomByName/{a}", method = RequestMethod.GET)
	public List<User> findCustomByReg(@PathVariable String a) {
		logger.info("Getting user with ID: {}.", a);
		return userRepository.findCustomByName(a);
	}
	
	@RequestMapping(value = "/findByNameAndRoll/{a}/{n}", method = RequestMethod.GET)
	public List<User> findByNameAndRoll(@PathVariable String a, @PathVariable int n) {
		logger.info("Getting users with name and Roll: {}.", a);
		return userRepository.findByNameAndRoll(a,n);
	}
	
	@RequestMapping(value = "/findByNameNotEqual/{a}", method = RequestMethod.GET)
	public List<User> findByNameAndRoll(@PathVariable String a) {
		logger.info("Getting users with name and Roll: {}.", a);
		return userRepository.findByNameNotEqual(a);
	}
}
