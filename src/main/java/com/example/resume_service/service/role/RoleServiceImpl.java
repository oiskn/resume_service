package com.example.resume_service.service.role;

import com.example.resume_service.exception.notfound.RoleNotFoundException;
import com.example.resume_service.model.Role;
import com.example.resume_service.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@EnableCaching
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Caching
    @Override
    @Transactional(readOnly = true)
    public Map<String, Role> getAllRoleMap() {
        return roleRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(Role::getAuthority, Function.identity()));
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByRole(String role) {
        return roleRepository
                .findByRole(role)
                .orElseThrow(RoleNotFoundException::new);
    }
}
