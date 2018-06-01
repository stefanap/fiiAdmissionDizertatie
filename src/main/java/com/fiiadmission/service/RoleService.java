package com.fiiadmission.service;

import com.fiiadmission.domain.Role;

public interface RoleService {

    Role findByRoleName(String roleName);
}
