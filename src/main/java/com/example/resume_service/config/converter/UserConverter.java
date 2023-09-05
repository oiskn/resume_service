package com.example.resume_service.config.converter;

import com.example.resume_service.model.Role;
import com.example.resume_service.model.User;
import com.example.resume_service.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserConverter {
    @Qualifier("userModelMapper")
    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder;


    public UserDto toDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        Set<String> roles = new HashSet<>();
        user.getRoles().forEach(element -> {
            roles.add(element.getAuthority());
        });
        userDto.setRoles(roles);
        return userDto;
    }

    public User toEntity(UserDto userDto, Map<String, Role> roleMap) {
        Set<Role> roles = new HashSet<>();
        User user = modelMapper.map(userDto, User.class);

        userDto.getRoles().forEach(element -> {
            Role role = roleMap.get(element);
            roles.add(role);
        });
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return user;
    }
}
