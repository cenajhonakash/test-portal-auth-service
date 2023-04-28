package com.testportal.authservice.controller.external;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testportal.authservice.dto.CredentialsDto;
import com.testportal.authservice.service.AuthenticationService;

@RestController
@RequestMapping("/v1/auth/external")
public class AuthControllerExternal {

	@Autowired
	private AuthenticationService authservice;

	@PostMapping("/generate-token")
	public ResponseEntity<String> createToken(@RequestBody(required = true) CredentialsDto userCred) {
		return new ResponseEntity<String>(authservice.generateToken(userCred), HttpStatus.CREATED);
	}

//	@PostMapping("/validate-token")
//	public ResponseEntity<Boolean> validateToken(@RequestBody(required = true) CredentialsDto user, final HttpServletRequest request) {
//
//		if (authservice.validateToken(request, user)) {
//			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
//		}
//		return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
//	}

	@PostMapping("/validate-token")
	public ResponseEntity<Boolean> validateToken(final HttpServletRequest request, @RequestBody(required = true) CredentialsDto credentials) {

		if (authservice.validateToken(request, credentials, true)) {
			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
	}
}
