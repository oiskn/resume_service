package com.example.resume_service.service.resume;

import com.example.resume_service.exception.notfound.ResumeNotFoundException;
import com.example.resume_service.model.Resume;
import com.example.resume_service.model.User;
import com.example.resume_service.repository.ResumeRepository;
import com.example.resume_service.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;

    private final UserService userService;

    private static final boolean RESUME_VISIBILITY = true;

    @Override
    @Transactional
    public void save(Resume resume) {
        Optional<Resume> existedResume = resumeRepository
                .findByUserLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        User user = userService
                .findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        existedResume.ifPresent(r -> resume.setId(r.getId()));
        resume.setUser(user);

        resumeRepository.save(resume);
    }

    @Override
    @Transactional(readOnly = true)
    public Resume findSummaryForAuthUser() {
        return resumeRepository
                .findByUserLogin(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(ResumeNotFoundException::new);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Resume resume = resumeRepository
                .findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        resumeRepository.delete(resume);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resume> findAllPublishedResume() {
        return resumeRepository.findAllByVisibility(RESUME_VISIBILITY);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteByUserLogin() {
        User user = userService
                .findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());

        resumeRepository.deleteByUserLogin(user.getLogin());
    }
}
