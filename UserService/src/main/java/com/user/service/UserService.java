package com.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.exception.ResourceNotFoundException;
import com.user.model.Hotel;
import com.user.model.Rating;
import com.user.model.User;
import com.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
		
	@Autowired
	private RestTemplate restTemplate;
	
	public List<User> getAllUser(){
		
			List<User> users = userRepo.findAll();
		users.forEach(user -> { ResponseEntity<List<Rating>> ratingOfUsers = restTemplate.exchange("http://RATINGSERVICE/ratings/users/"+user.getUserId(),
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Rating>>(){});
		        user.setRatings(ratingOfUsers.getBody());
		        });
		
		return users;
	}
	
	public User saveUser(User user) {
		String random = UUID.randomUUID().toString();
		user.setUserId(random);
		return userRepo.save(user);
	}
	
	public User getUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
        ResponseEntity<List<Rating>> ratingsOfUsers =  restTemplate.exchange("http://RATINGSERVICE/ratings/users/"+user.getUserId(),
        		HttpMethod.GET, null,
        		new ParameterizedTypeReference<List<Rating>>(){});
        user.setRatings(ratingsOfUsers.getBody());
	    return user;
	}
	
	public User fallbackuser(String userId) {
		return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
	}

	public List<User> getAllUserFallBack() {
		return userRepo.findAll();
	}
}
