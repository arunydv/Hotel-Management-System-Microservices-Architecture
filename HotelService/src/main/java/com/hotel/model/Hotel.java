package com.hotel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
	
	@Id
	@Column(name = "HotelId")
	private String hotleId;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "Location", nullable = false)
	private String location;
	@Column(name = "About", nullable = false)
	private String about;

}
