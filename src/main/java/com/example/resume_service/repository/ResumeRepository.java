package com.example.resume_service.repository;

import com.example.resume_service.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Resume findByUserId(Long id);

    Optional<Resume> findByUserLogin(String login);

    boolean existsByUserLogin(String login);

    List<Resume> findAllByVisibility(Boolean visibility);

    void deleteByUserLogin(String login);

}
