package com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.model.dto.validation;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.Getter;

public class ScoreValidator implements ConstraintValidator<ValidScore, String>{

    @Getter
    private static final List<String> scores = Arrays.asList(
        "A+", "A", "A-",
        "B+", "B", "B-",
        "C+", "C", "C-",
        "D+", "D", "D-",
        "F"
    );

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return scores.stream().anyMatch(score -> score.equals(value));
    }

}
