package com.testportal.authservice.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.testportal.authservice.config.CustomUserDetails;
import com.testportal.authservice.config.CustomUserDetailsService;
import com.testportal.authservice.dto.CredentialsDto;
import com.testportal.authservice.dto.UserDto;

@Component
public class AuthHelper {

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	private final BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

	public String generateToken(UserDto user) {
		return jwtHelper.generateToken(user);
	}

	public Boolean validateToken(String token) {
		String username = jwtHelper.extractUsername(token);
		CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);

		return jwtHelper.validateToken(token, CredentialsDto.builder().username(userDetails.getUsername()).build(), username);
	}

	public Boolean validateExternalUserAndToken(CredentialsDto userCred, String token) {
		// CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByCredentials(userCred);
		CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userCred.getUsername());
		if (!passEncoder.matches(userCred.getPassword(), userDetails.getPassword())) {
			throw new AccessDeniedException("Invalid Credentials!");
		}
		// customUserDetailsService.loadUserByCredentials(userCred);
		return jwtHelper.validateToken(token.substring(7), userCred, null);
	}

}
