package com.example.resume_service.config.converter;

import com.example.resume_service.model.Image;
import com.example.resume_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ImageConverter {

    public Image toImage(MultipartFile multipartImage, User user) throws IOException {
        return Image.builder()
                .name(multipartImage.getName())
                .content(multipartImage.getBytes())
                .user(user)
                .build();
    }
}
