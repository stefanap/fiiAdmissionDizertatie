package com.fiiadmission.service.impl;

import com.fiiadmission.domain.Role;
import com.fiiadmission.repository.RoleRepository;
import com.fiiadmission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role findByRoleName(String roleName){
        return roleRepository.findByRoleName(roleName);
    }
}
