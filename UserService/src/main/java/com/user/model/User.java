package com.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "micro_user")
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
}
