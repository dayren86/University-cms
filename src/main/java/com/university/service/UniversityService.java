package com.university.service;

import com.university.model.*;
import com.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;

    public void createUniversity(University university) {
        universityRepository.save(university);
    }

    public List<University> findAllTimetable() {
        return universityRepository.findAll();
    }

    public University findUniversityById(Long idUniversity) {
        return universityRepository.findById(idUniversity).orElseThrow();
    }
}
