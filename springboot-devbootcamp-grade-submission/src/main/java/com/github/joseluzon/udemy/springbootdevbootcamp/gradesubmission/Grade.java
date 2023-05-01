package com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder =  true)
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
