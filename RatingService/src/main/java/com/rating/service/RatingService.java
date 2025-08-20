package com.rating.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.model.Rating;
import com.rating.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepo;
	
	public List<Rating> getAllRatings(){
		return ratingRepo.findAll();
	}
	
	public Rating create(Rating rating) {
		return ratingRepo.save(rating);
	}
	
	public List<Rating> findByUser(String userId){
		 return ratingRepo.findByUserId(userId);
	}
	
	public List<Rating> findByHotel(String hotelId){
		return ratingRepo.findByHotelId(hotelId);
	}
}
