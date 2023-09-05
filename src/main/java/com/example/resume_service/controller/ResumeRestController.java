package com.example.resume_service.controller;

import com.example.resume_service.config.converter.ResumeConverter;
import com.example.resume_service.model.Resume;
import com.example.resume_service.model.dto.ResumeDto;
import com.example.resume_service.service.resume.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.resume_service.controller.util.UniformResourceLocatorUtils.*;

@RestController
@AllArgsConstructor
public class ResumeRestController {
    private ResumeService resumeService;

    private ResumeConverter resumeConverter;

    @PostMapping(SAVE_RESUME)
    public ResponseEntity<Void> save(@RequestBody ResumeDto resumeDto) {
        Resume resume = resumeConverter.toEntity(resumeDto);
        resumeService.save(resume);

        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE_USERS_RESUME)
    public ResponseEntity<Void> deleteUserResume() {
        resumeService.deleteByUserLogin();

        return ResponseEntity.ok().build();
    }
    @DeleteMapping(DELETE_RESUME_BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        resumeService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(MY_RESUME)
    public ResponseEntity<ResumeDto> get() {
        Resume resume = resumeService.findSummaryForAuthUser();
        ResumeDto resumeDto = resumeConverter.toDto(resume);

        return ResponseEntity.ok(resumeDto);
    }

    @GetMapping(ALL_PUBLISHED_RESUME)
    public ResponseEntity<List<ResumeDto>> findAllPublishedResume() {
        List<ResumeDto> resumeList = resumeService
                .findAllPublishedResume()
                .stream()
                .map(resumeConverter::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resumeList);
    }

    @GetMapping(ALL_SAVED_RESUME)
    public ResponseEntity<List<ResumeDto>> findAllResume() {
        List<ResumeDto> resumeList = resumeService
                .findAll()
                .stream()
                .map(resumeConverter::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resumeList);
    }
}
