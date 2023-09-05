package com.example.resume_service.service.resume;

import com.example.resume_service.model.Resume;

import java.util.List;

public interface ResumeService {

    void save(Resume resume);

    void delete(Long id);

    Resume findSummaryForAuthUser();

    List<Resume> findAllPublishedResume();

    List<Resume> findAll();

    void deleteByUserLogin();
}
