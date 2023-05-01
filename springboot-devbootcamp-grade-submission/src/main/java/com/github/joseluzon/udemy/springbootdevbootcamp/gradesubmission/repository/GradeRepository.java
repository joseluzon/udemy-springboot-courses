package com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.model.dto.Grade;
import lombok.Getter;

@Repository
public class GradeRepository {
    
    @Getter
    private List<Grade> studentGrades = new ArrayList<>();

    public boolean existsGrade(final UUID uuid) {
        return getGradeIfAny(uuid).isPresent();
    }

    public Optional<Grade> getGrade(final UUID uuid) {
        final var gradeOptional = getGradeIfAny(uuid);
        // returns a copy if any
        return gradeOptional.isPresent() 
            ? Optional.of(gradeOptional.get().toBuilder().build())
            : Optional.empty();
    }

    private Optional<Grade> getGradeIfAny(final UUID uuid) {
        return studentGrades.stream().filter(grade -> grade.getId().equals(uuid)).findFirst();
    }

    public void addGrade(final Grade grade) {
        studentGrades.add(grade);
    }
    
    public void updateGrade(final UUID uuid, final Grade grade) {
        final var currentGrade = getGradeIfAny(uuid);
        if (currentGrade.isPresent()) {
            final var theCurrentGrade = currentGrade.get();
            theCurrentGrade.setName(grade.getName());
            theCurrentGrade.setScore(grade.getScore());
            theCurrentGrade.setSubject(grade.getSubject());
        }
    }
}
