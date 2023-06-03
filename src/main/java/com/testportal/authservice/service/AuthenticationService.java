package com.testportal.authservice.service;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.testportal.authservice.config.CustomUserDetails;
import com.testportal.authservice.constants.AppConstants;
import com.testportal.authservice.dto.CredentialsDto;
import com.testportal.authservice.dto.UserDto;
import com.testportal.authservice.helper.AuthHelper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService {

	private Logger log = LoggerFactory.getLogger(AuthenticationService.class);
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private AuthHelper authHelper;

	public String generateToken(CredentialsDto userCred) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCred.getUsername(), userCred.getPassword()));

		if (authenticate.isAuthenticated()) {

			log.info("user authenticated successfully with username: {}", userCred.getUsername());

			CustomUserDetails ud = (CustomUserDetails) authenticate.getPrincipal();
			UserDto user = mapper.map(ud.getUser(), UserDto.class);

			return authHelper.generateToken(user);
			// return jwtHelper.generateToken(user);
		}
		log.error("user authentication failed with username: {}", userCred.getUsername());

		return null;
	}

	public Boolean validateToken(HttpServletRequest request, CredentialsDto userCred, boolean isExternal) {
		String token = request.getHeader(AppConstants.AUTHORIZATION.value);
		log.info("requested resource is: {}", request.getRequestURI());
		if (token == null || !token.startsWith(AppConstants.BEARER.value)) {
			log.error("Invalid token: {}", token);
			throw new AccessDeniedException("Invalid token passed!");
		}
		if (isExternal) {
			return authHelper.validateExternalUserAndToken(userCred, token);
		}
		return authHelper.validateToken(token.substring(7), request.getHeader(AppConstants.EXCH_REQ_METHOD.value), request.getHeader(AppConstants.EXCH_REQ_URI.value));
	}
}
