package com.testportal.authservice.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.testportal.authservice.dto.UserDto;

import lombok.ToString;

@ToString
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -3081775250917107683L;

	private UserDto user;

	public UserDto getUser() {
		return this.user;
	}

	public CustomUserDetails(UserDto user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> grantedAuthority = new HashSet<>();
		this.user.getRole().getRoles().forEach(userRole -> grantedAuthority.add(Authority.builder().authority(userRole).build()));
		return grantedAuthority;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
