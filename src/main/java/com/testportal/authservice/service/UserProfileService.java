package com.testportal.authservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.testportal.authservice.dto.CredentialsDto;
import com.testportal.authservice.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserProfileService {

	private Logger log = LoggerFactory.getLogger(UserProfileService.class);

	@Value("${ups.host}")
	private String upsHost;
	@Value("${ups.get-user-details-path}")
	private String getUserPath;
	@Value("${ups.get-user-details-ext-path}")
	private String getUserPathExt;
	@Value("${ups.basic-auth.username}")
	private String basicAuthUserName;
	@Value("${ups.basic-auth.password}")
	private String basicAuthPass;
	@Autowired
	private RestTemplate rest;
	private final BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

	public UserDto getUserDetailsFromUserCredentials(String username, CredentialsDto credentials, boolean isExternal) {

		final String url = isExternal ? upsHost + getUserPathExt : upsHost + getUserPath + username;
		// final String url = username == null ? upsHost + getUserPath + credentials.getUsername() : upsHost + getUserPath + username;
		log.info("created url to fetch user is: {}", url);

		final HttpHeaders headers = new HttpHeaders();
		ResponseEntity<UserDto> response = null;
		// headers.setBasicAuth(username, url);
		if (isExternal) {
			credentials.setPassword(passEncoder.encode(credentials.getPassword()));
			HttpEntity<CredentialsDto> request = new HttpEntity<CredentialsDto>(credentials, headers);
			response = rest.exchange(url, HttpMethod.POST, request, UserDto.class);
		}
		try {
			response = rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), UserDto.class);
		} catch (RestClientException e) {
			log.error("Error connecting rest client ups with error: {}", e.getMessage());
			// throw new RestServiceCallFailedException("Error connecting rest client ups");
		}
		response = rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), UserDto.class);

		return response.getBody();
	}
}
