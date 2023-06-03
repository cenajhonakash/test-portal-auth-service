package com.testportal.authservice.controller.internal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testportal.authservice.service.AuthenticationService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/auth/internal")
@Slf4j
public class AuthControllerInternal {

	@Autowired
	private AuthenticationService authservice;

	private int retryCount = 0;

//	@PostMapping("/generate-token")
//	public ResponseEntity<String> createToken(@RequestBody(required = true) CredentialsDto userCred) {
//		return new ResponseEntity<String>(authservice.generateToken(userCred), HttpStatus.CREATED);
//	}

//	@PostMapping("/validate-token")
//	public ResponseEntity<Boolean> validateToken(@RequestBody(required = true) CredentialsDto user, final HttpServletRequest request) {
//
//		if (authservice.validateToken(request, user)) {
//			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
//		}
//		return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
//	}

	/*
	 * @CircuitBreaker is having higher priority than @Retry. Thus the retry happens once instead of 3 and fallback method is executed
	 */
	@PostMapping("/validate-token")
	@CircuitBreaker(name = "ups_circuit_breaker", fallbackMethod = "upsBreakerFallBack")
	@Retry(name = "ups")
	public ResponseEntity<Boolean> validateToken(final HttpServletRequest request) {
		log.info("Retry count : {}", retryCount);
		retryCount++;
		if (authservice.validateToken(request, null, false)) {
			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		}
		return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
	}

	/*
	 * fallback method for UPS circuit breaker
	 */
	public ResponseEntity<Boolean> upsBreakerFallBack(final HttpServletRequest request, Exception e) {
		log.warn("upsBreakerFallBack() invoked as UPS service is down with error : {}", e.getMessage());
		log.info("Retry count before executing fallback: {}", retryCount);
		retryCount = 0;
		return new ResponseEntity<>(Boolean.FALSE, HttpStatus.FORBIDDEN);
	}
}