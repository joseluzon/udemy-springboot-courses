package com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.model.dto.Grade;
import com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.repository.GradeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Ctor. is autowired
public class GradeService {
    // @Autowired by Ctor.
    private final GradeRepository gradeRepository;

    public List<Grade> getStudentGrades() {
        return gradeRepository.getStudentGrades();
    }

    public boolean existsGrade(final UUID uuid) {
        return gradeRepository.existsGrade(uuid);
    }

    public Grade getGrade(final UUID uuid) {
        final var gradeOptional = gradeRepository.getGrade(uuid);
        return gradeOptional.isPresent() ? gradeOptional.get() : Grade.builder().build();
    }

    private void addGrade(final Grade grade) {
        gradeRepository.addGrade(grade);
    }
    
    private void updateGrade(final UUID uuid, final Grade grade) {
        gradeRepository.updateGrade(uuid, grade);
    }

    public void submitGrade(final Grade grade) {
        if (existsGrade(grade.getId())) {
            updateGrade(grade.getId(), grade);
        } else {
            addGrade(grade);
        }
    }
}
