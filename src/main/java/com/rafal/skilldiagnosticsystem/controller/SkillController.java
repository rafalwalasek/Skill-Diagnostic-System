package com.rafal.skilldiagnosticsystem.controller;

import com.rafal.skilldiagnosticsystem.dto.AnswerRequest;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import com.rafal.skilldiagnosticsystem.service.FileManagerService;
import com.rafal.skilldiagnosticsystem.service.SkillAssessmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class SkillController {
    private final SkillAssessmentService skillAssessmentService;
    private final QuestionRepository questionRepository;
    private final FileManagerService fileManagerService;

    public SkillController(SkillAssessmentService skillAssessmentService,
                           QuestionRepository questionRepository,
                           FileManagerService fileManagerService) {
        this.skillAssessmentService = skillAssessmentService;
        this.questionRepository = questionRepository;
        this.fileManagerService = fileManagerService;
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    @PostMapping("/submit")
    public double submitAnswers(@RequestBody AnswerRequest answerRequest) {
        List<Question> questions = questionRepository.findAll();

        double result = skillAssessmentService.checkPerformance(answerRequest.getAnswers(), questions);
        fileManagerService.writeToFile(List.of(result));

        return result;
    }
    @GetMapping("/results")
    public List<Double> readFromFile() {
        return fileManagerService.readFromFile();
    }
}
