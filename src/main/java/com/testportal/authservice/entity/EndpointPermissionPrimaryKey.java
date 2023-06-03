package com.testportal.authservice.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EndpointPermissionPrimaryKey implements Serializable {

	private static final long serialVersionUID = 100004567L;

	private String endpoint;
	private String method;
	private String role;
}
