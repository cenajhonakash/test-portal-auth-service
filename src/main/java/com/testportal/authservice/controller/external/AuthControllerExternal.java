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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/auth/external")
@Slf4j
public class AuthControllerExternal {

	@Autowired
	private AuthenticationService authservice;
	private int retryCount = 0;

	@PostMapping("/generate-token")
	@CircuitBreaker(name = "ups_circuit_breaker", fallbackMethod = "upsBreakerFallBack")
	@Retry(name = "ups")
	public ResponseEntity<String> createToken(@RequestBody(required = true) CredentialsDto userCred) {
		log.info("Retry count : {}", retryCount);
		retryCount++;
		return new ResponseEntity<String>(authservice.generateToken(userCred), HttpStatus.OK);
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
			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		}
		return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
	}

	/*
	 * fallback method for UPS circuit breaker
	 */
	public ResponseEntity<String> upsBreakerFallBack(CredentialsDto userCred, Exception e) {
		log.warn("upsBreakerFallBack() invoked for method createToken() as UPS service is down with error : {}", e.getMessage());
		log.info("Retry count before executing fallback: {}", retryCount);
		retryCount = 0;
		return new ResponseEntity<>("Few Services are down at this moment. Please try after sometime.", HttpStatus.REQUEST_TIMEOUT);
	}
}
