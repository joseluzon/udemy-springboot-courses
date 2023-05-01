package com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScoreValidator implements ConstraintValidator<ValidScore, String>{

    List<String> scores = Arrays.asList(
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
