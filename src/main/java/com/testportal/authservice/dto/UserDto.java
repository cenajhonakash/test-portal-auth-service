package com.testportal.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	private Long userID;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String username;
	private boolean enabled;
	private String password;

}
