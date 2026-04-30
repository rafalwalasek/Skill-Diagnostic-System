package com.rafal.skilldiagnosticsystem.controller;

import com.rafal.skilldiagnosticsystem.dto.AnswerRequest;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import com.rafal.skilldiagnosticsystem.service.SkillAssessmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class SkillController {
    private final SkillAssessmentService skillAssessmentService;
    private final QuestionRepository questionRepository;

    public SkillController(SkillAssessmentService skillAssessmentService, QuestionRepository questionRepository) {
        this.skillAssessmentService = skillAssessmentService;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    @PostMapping("/submit")
    public double submitAnswers(@RequestBody AnswerRequest answerRequest) {
        List<Question> questions = questionRepository.findAll();

        return skillAssessmentService.checkPerformance(answerRequest.getAnswers(), questions);
    }
}
