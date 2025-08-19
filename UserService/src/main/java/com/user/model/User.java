package com.user.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "micro_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@Column(name = "Id")
	private String userId;
	@Column(name = "Name",nullable = false, length = 10)
	private String name;
	@Column(name = "Email",nullable = false)
    private String email;
	@Column(name="About",nullable = false)
	private String about;
	
	@jakarta.persistence.Transient
	private List<Rating> ratings = new ArrayList<>();
}
