package com.testportal.authservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.testportal.authservice.dto.UserDto;
import com.testportal.authservice.service.UserProfileService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	private UserProfileService ups;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto u = ups.getUserDetailsFromUserCredentials(username, null, false);
		if (u == null) {
			log.error("user not found with username; {}", username);
		}
		return new CustomUserDetails(u);
	}

//	public UserDetails loadUserByCredentials(CredentialsDto credentials) {
//		UserDto u = ups.getUserDetailsFromUserCredentials(null, credentials, false);
//		if (u == null) {
//			log.error("user not found with username: {}", credentials.getUsername());
//			throw new AccessDeniedException("Unauthorized!");
//		}
//		return new CustomUserDetails(u);
//	}
}
