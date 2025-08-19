package com.hotel.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.exception.ResourceNotFoundException;
import com.hotel.model.Hotel;
import com.hotel.repository.HotelRepository;

@Service	
public class HotelService {

	@Autowired
	private HotelRepository hotelRepo;
	
	public List<Hotel> getAllHotels(){
		return hotelRepo.findAll();
	}
	
	public Hotel getHotelById(String id) {
		return hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hoetl not found!!"));
	}
	
	public Hotel createHotel(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
		hotel.setHotleId(hotelId);
		return hotelRepo.save(hotel);
	}
}
