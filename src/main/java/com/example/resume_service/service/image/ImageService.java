package com.example.resume_service.service.image;

import com.example.resume_service.model.Image;

public interface ImageService {

    void save(Image image);

    Image getImageAuthUser();

    Image findByUserId(Long id);

    boolean existByUserId(Long id);

    boolean exist();

    void delete();
}
