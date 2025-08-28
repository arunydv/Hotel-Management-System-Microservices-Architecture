package com.rating.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rating.model.Hotel;
import com.rating.model.Rating;
import com.rating.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Rating> getAllRatings(){
		return ratingRepo.findAll();
	}
	
	public Rating create(Rating rating) {
		return ratingRepo.save(rating);
	}
	
	public List<Rating> findByUser(String userId){
		 List<Rating> ratings = ratingRepo.findByUserId(userId);
		 ratings.forEach(rating ->{ResponseEntity<Hotel> hotel = restTemplate.exchange("http://HOTELSERVICE/hotels/"+rating.getHotelId()
		        ,HttpMethod.GET
				,null
				,new ParameterizedTypeReference<Hotel>() {
				});
		        rating.setHotel(hotel.getBody());
		 });
		 return ratings;
	}
	
	public List<Rating> findByHotel(String hotelId){
		return ratingRepo.findByHotelId(hotelId);
	}

	public List<Rating> findByUserFallBack(String userId) {
		return ratingRepo.findByUserId(userId);
	}
}
