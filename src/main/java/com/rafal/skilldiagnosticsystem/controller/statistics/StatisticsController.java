package com.rafal.skilldiagnosticsystem.controller.statistics;

import com.rafal.skilldiagnosticsystem.service.QuestionService;
import com.rafal.skilldiagnosticsystem.service.QuizAttemptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/quiz")
@RestController
public class StatisticsController {
    private final QuestionService questionService;
    private final QuizAttemptService quizAttemptService;

    public StatisticsController(QuestionService questionService,
                                QuizAttemptService quizAttemptService) {
        this.questionService = questionService;
        this.quizAttemptService = quizAttemptService;
    }

    @GetMapping("/allQuestionCount")
    public long getAllQuestionCount() {
        return questionService.getAllQuestionCount();
    }
    @GetMapping("/categoryQuestionCount")
    public long getCategoryQuestionCount(@RequestParam String category) {
        return questionService.getCategoryQuestionCount(category);
    }
    @GetMapping("/attemptsCount")
    public long getAttemptsCount(@RequestParam String category) {
        return quizAttemptService.getCategoryAttempts(category);
    }
}
