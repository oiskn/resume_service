package com.example.resume_service.service.role;

import com.example.resume_service.model.Role;

import java.util.Map;

public interface RoleService {
    Map<String, Role> getAllRoleMap();

    Role findByRole(String role);
}
