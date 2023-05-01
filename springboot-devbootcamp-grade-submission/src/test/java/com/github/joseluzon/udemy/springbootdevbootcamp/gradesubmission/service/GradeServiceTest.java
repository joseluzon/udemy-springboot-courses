package com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.github.javafaker.Faker;
import com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.model.dto.Grade;
import com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.model.dto.validation.ScoreValidator;
import com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.repository.GradeRepository;

/*
 * Unit testing for isolated components --> Spring context is not loaded.
 * 
 * First Unit testing -> then Integration testing.
 */

// @RunWith(MockitoJUnitRunner.class) // JUnit 4
@ExtendWith(MockitoExtension.class) // JUnit 5
public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;
    @InjectMocks
    private GradeService gradeService;

    private Faker faker = new Faker();

    @Test
    void testExistsGrade() {
        final var testData = List.of(
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build(),
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build(),
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build());

        testData.stream()
                .forEach(d -> when(gradeRepository.existsGrade(d.getId())).thenReturn(true));

        testData.stream().forEach(d -> assertTrue(gradeService.existsGrade(d.getId())));
    }

    @Test
    void testGetGrade() {
        final var testData = List.of(
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build(),
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build(),
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build());

        testData.stream()
                .forEach(d -> when(gradeRepository.getGrade(d.getId())).thenReturn(Optional.of(d)));

        testData.stream().forEach(d -> assertEquals(d, gradeService.getGrade(d.getId())));
    }

    @Test
    void testGetStudentGrades() {
        final var testData = List.of(
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build(),
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build(),
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build());
        when(gradeRepository.getStudentGrades()).thenReturn(testData);

        final var result = gradeService.getStudentGrades();

        assertIterableEquals(testData, result);
    }

    @Test
    void testSubmitAddGrade() {
        final var gradeToAdd =
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build();
        when(gradeRepository.existsGrade(gradeToAdd.getId())).thenReturn(false);

        gradeService.submitGrade(gradeToAdd);

        verify(gradeRepository, times(1)).addGrade(gradeToAdd);
    }

    @Test
    void testSubmitUpdateGrade() {
        final var gradeToUpdate =
                Grade.builder().name(faker.harryPotter().character())
                        .subject(faker.harryPotter().spell())
                        .score(ScoreValidator.getScores()
                                .get(new Random().nextInt(ScoreValidator.getScores().size())))
                        .build();
        when(gradeRepository.existsGrade(gradeToUpdate.getId())).thenReturn(true);

        gradeService.submitGrade(gradeToUpdate);

        verify(gradeRepository, times(1)).updateGrade(gradeToUpdate.getId(), gradeToUpdate);
    }
}
