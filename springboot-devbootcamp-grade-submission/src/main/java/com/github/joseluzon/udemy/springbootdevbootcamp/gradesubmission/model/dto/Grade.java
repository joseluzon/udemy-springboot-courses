package com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.model.dto;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.model.dto.validation.ValidScore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder =  true)
@EqualsAndHashCode(of = "id")
public class Grade {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Subject cannot be blank")
    private String subject;
    @NotBlank(message = "Score cannot be blank")
    @ValidScore(message = "Invalid score")
    private String score;
    @Builder.Default
    private UUID id = UUID.randomUUID();
}
