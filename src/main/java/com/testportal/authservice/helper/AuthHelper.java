package com.testportal.authservice.helper;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.testportal.authservice.config.CustomUserDetails;
import com.testportal.authservice.config.CustomUserDetailsService;
import com.testportal.authservice.dto.CredentialsDto;
import com.testportal.authservice.dto.UserDto;
import com.testportal.authservice.entity.EndpointPermission;
import com.testportal.authservice.repository.EndpointPermissionRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthHelper {

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private EndpointPermissionRepository permissionRepository;
	private final BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

	public String generateToken(UserDto user) {
		return jwtHelper.generateToken(user);
	}

	public Boolean validateToken(String token, String exchangeRequestMethod, String exchangeRequestUri) {
		String username = jwtHelper.extractUsername(token);
		CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
		/*
		 * validate the roles are allowed to access the endpoints
		 */
		if (exchangeRequestMethod != null && exchangeRequestUri != null) {
			List<EndpointPermission> endpointsPermitted = permissionRepository.findAll();
			Optional<EndpointPermission> maybeEntry = endpointsPermitted.stream().filter(entity -> exchangeRequestUri.contains(entity.getEndpoint())
					&& entity.getMethod().equalsIgnoreCase(exchangeRequestMethod) && userDetails.getUser().getRole().getRoles().contains(entity.getRole())).findAny();
			if (maybeEntry.isEmpty()) {
				log.warn("Unauthorized access to requested resource : {} by user with role : {} ", exchangeRequestUri, userDetails.getUser().getRole().toString());
				throw new AccessDeniedException("Unauthorized access to requested resource");
			}
		}
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
