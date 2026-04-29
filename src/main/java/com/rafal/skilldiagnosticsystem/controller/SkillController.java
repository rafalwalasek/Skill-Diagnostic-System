package com.rafal.skilldiagnosticsystem.controller;

import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.service.SkillAssessmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class SkillController {
    private final SkillAssessmentService skillAssessmentService;

    public SkillController(SkillAssessmentService skillAssessmentService) {
        this.skillAssessmentService = skillAssessmentService;
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return skillAssessmentService.listQuestions();
    }
    @PostMapping("/submit")
    public double submitAnswers() {
        return skillAssessmentService.checkPerformance(answersUser, questions);
    }
}
