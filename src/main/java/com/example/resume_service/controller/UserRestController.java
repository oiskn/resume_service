package com.example.resume_service.controller;

import com.example.resume_service.config.converter.UserConverter;
import com.example.resume_service.model.Role;
import com.example.resume_service.model.User;
import com.example.resume_service.model.dto.UserDto;
import com.example.resume_service.service.role.RoleService;
import com.example.resume_service.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.example.resume_service.controller.util.UniformResourceLocatorUtils.*;

@RestController
@AllArgsConstructor
public class UserRestController {

    private final UserConverter userConverter;
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping(SAVE_USER)
    public ResponseEntity<Void> save(@RequestBody UserDto userDto) {
        Map<String, Role> roleMap = roleService.getAllRoleMap();
        User user = userConverter.toEntity(userDto, roleMap);
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(GET_USER)
    public ResponseEntity<UserDto> getUser() {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        UserDto userDto = userConverter.toDto(user);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping(CHECK_AUTH_USER)
    public boolean checkAuthUser() {
        return userService.checkAuthUser();
    }

}
