package com.example.resume_service.service.image;

import com.example.resume_service.exception.notfound.ImageNotFoundException;
import com.example.resume_service.model.Image;
import com.example.resume_service.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public void save(Image image) {
        Optional<Image> existedImage = imageRepository
                .findAllByUserLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        existedImage.ifPresent(i -> image.setId(i.getId()));

        imageRepository.save(image);
    }

    @Override
    @Transactional(readOnly = true)
    public Image getImageAuthUser() {
        return imageRepository
                .findAllByUserLogin(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(ImageNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Image findByUserId(Long id) {
        return imageRepository
                .findByUserId(id)
                .orElseThrow(ImageNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existByUserId(Long id) {
        return imageRepository.existsByUserId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exist() {
        return imageRepository.existsByUserLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    @Transactional
    public void delete() {
        imageRepository.deleteByUserLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
