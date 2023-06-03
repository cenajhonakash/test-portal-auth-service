package com.testportal.authservice.exception;

public class RestServiceCallFailedException extends Exception {

	public RestServiceCallFailedException() {
		super("Rest call failed!");
	}

	public RestServiceCallFailedException(String message) {
		super(message);
	}
}
