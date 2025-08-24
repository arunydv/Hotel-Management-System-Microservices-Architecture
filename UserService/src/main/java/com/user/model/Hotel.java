package com.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Hotel {
	private String hotleId;
	private String name;
	private String location;
	private String about;
}
