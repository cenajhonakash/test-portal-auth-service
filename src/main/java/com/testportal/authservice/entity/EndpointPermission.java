package com.testportal.authservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "api_permission")
@IdClass(EndpointPermissionPrimaryKey.class)
public class EndpointPermission {
	@Id
	@Column(name = "created_at", updatable = false, insertable = false)
	private String endpoint;
	@Id
	@Column(name = "created_at", updatable = false, insertable = false)
	private String method;
	@Id
	@Column(name = "created_at", updatable = false, insertable = false)
	private String role;
}
