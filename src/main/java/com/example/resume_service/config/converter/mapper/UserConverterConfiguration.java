package com.example.resume_service.config.converter.mapper;

import com.example.resume_service.model.User;
import com.example.resume_service.model.dto.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UserConverterConfiguration {
    @Bean("userModelMapper")
    public ModelMapper getUserModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<User, UserDto>() {
            @Override
            protected void configure() {
                skip(destination.getRoles());
            }
        });

        modelMapper.addMappings(new PropertyMap<UserDto, User>() {
            @Override
            protected void configure() {
                skip(destination.getRoles());
                skip(destination.getPassword());
            }
        });

        return modelMapper;
    }
}
