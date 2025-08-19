package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.Hotel;
import com.hotel.service.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel() {
		return ResponseEntity.ok(hotelService.getAllHotels());
	}

	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelByGivenId(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
	}
	
	@PostMapping
	public ResponseEntity<Hotel> createdHotel(@RequestBody Hotel hotel){
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
	}
}
