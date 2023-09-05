package com.example.resume_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {

    private Long id;

    private Long age;

    private String firstName;

    private String lastName;

    private String education;

    private String experience;

    private String skills;

    private String about;

    private Boolean visibility;

    private String imageUserId;
}
