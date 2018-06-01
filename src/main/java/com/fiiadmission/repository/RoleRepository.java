package com.fiiadmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.fiiadmission.domain.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByRoleName(String roleName);
}
