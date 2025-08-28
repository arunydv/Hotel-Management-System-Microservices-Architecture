package com.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.User;
import com.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
 
	@GetMapping
	@CircuitBreaker(name = "ratingWithHotelBreaker", fallbackMethod = "ratingWithHotelFallBack")
	public ResponseEntity<List<User>> getUsers() {
		List<User> user1 = userService.getAllUser();
		return ResponseEntity.ok(user1);
	}
	
	public ResponseEntity<List<User>> ratingWithHotelFallBack(Exception e){
		List<User> users = userService.getAllUserFallBack();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable String userId){
		User user1 = userService.getUser(userId);
		return ResponseEntity.ok(user1);
	}
	
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception e){
		User user = userService.fallbackuser(userId);
		return ResponseEntity.ok(user);
	}
}
