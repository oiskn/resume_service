package com.example.resume_service.repository;

import com.example.resume_service.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByUserId(Long id);

    Optional<Image> findAllByUserLogin(String login);

    boolean existsByUserLogin(String login);

    boolean existsByUserId(Long id);

    void deleteByUserLogin(String login);
}
