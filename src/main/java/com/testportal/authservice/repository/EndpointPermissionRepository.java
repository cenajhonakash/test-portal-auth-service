package com.testportal.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testportal.authservice.entity.EndpointPermission;
import com.testportal.authservice.entity.EndpointPermissionPrimaryKey;

public interface EndpointPermissionRepository extends JpaRepository<EndpointPermission, EndpointPermissionPrimaryKey> {

}
