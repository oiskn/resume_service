package com.example.resume_service.config.converter.mapper;

import com.example.resume_service.model.Resume;
import com.example.resume_service.model.dto.ResumeDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ResumeConverterConfiguration {
    @Bean("resumeModelMapper")
    public ModelMapper getResumeModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<ResumeDto, Resume>() {
            @Override
            protected void configure() {
                skip(destination.getUser());
            }
        });

        return modelMapper;
    }
}