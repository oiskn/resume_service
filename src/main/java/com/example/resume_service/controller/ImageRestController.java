package com.example.resume_service.controller;

import com.example.resume_service.config.converter.ImageConverter;
import com.example.resume_service.model.Image;
import com.example.resume_service.model.User;
import com.example.resume_service.service.image.ImageService;
import com.example.resume_service.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.resume_service.controller.util.UniformResourceLocatorUtils.*;

@RestController
@AllArgsConstructor
public class ImageRestController {

    private final ImageService imageService;

    private final UserService userService;

    private final ImageConverter imageConverter;

    @PostMapping(SAVE_IMAGE)
    public ResponseEntity<Void> uploadImage(@RequestParam MultipartFile multipartImage) throws Exception {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Image image = imageConverter.toImage(multipartImage, user);
        imageService.save(image);

        return ResponseEntity.ok().build();
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ByteArrayResource> downloadImage() {
        byte[] image = imageService.getImageAuthUser().getContent();
        return ResponseEntity.ok(new ByteArrayResource(image));
    }

    @GetMapping(value = FIND_IMAGE, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ByteArrayResource> findImage(@PathVariable Long id) {
        byte[] image = imageService.findByUserId(id).getContent();
        return ResponseEntity.ok(new ByteArrayResource(image));
    }

    @DeleteMapping(DELETE_IMAGE)
    public ResponseEntity<Void> delete(){
        imageService.delete();
        return ResponseEntity.ok().build();
    }
}
