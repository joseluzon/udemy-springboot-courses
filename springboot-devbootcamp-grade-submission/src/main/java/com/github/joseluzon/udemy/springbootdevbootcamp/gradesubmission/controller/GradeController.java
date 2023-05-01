package com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.controller;

import java.util.UUID;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.model.dto.Grade;
import com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission.service.GradeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/grades")
@RequiredArgsConstructor // Ctor. is autowired
public class GradeController {

    // @Autowired by Ctor.
    private final GradeService gradeService;

    @GetMapping("")
    public String getGrades(final Model model) {
        model.addAttribute("grades", gradeService.getStudentGrades());
        return "grades"; // html view
    }

    @GetMapping("/form")
    public String getForm(final Model model, @RequestParam(required = false) final UUID uuid) {
        model.addAttribute("grade", gradeService.getGrade(uuid));
        return "form";
    }

    @PostMapping("/submitGrade")
    public String submitGrade(@Valid final Grade grade, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        gradeService.submitGrade(grade);
        return "redirect:/grades";
    }
}
