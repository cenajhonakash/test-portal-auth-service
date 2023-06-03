package com.testportal.authservice.config;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Authority implements GrantedAuthority {

	private String authority;

	/*
	 * public void setAuthority(String authority) { this.authority = authority; }
	 */

//	public Authority(String authority) {
//		super();
//		this.authority = authority;
//	}

	@Override
	public String getAuthority() {
		return this.authority;
	}
}
