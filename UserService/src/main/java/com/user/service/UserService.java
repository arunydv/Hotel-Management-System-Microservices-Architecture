package com.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.exception.ResourceNotFoundException;
import com.user.model.User;
import com.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
		
	public List<User> getAllUser(){
		return userRepo.findAll();
	}
	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	public User getUser(String userId) {
		return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
	}

}
