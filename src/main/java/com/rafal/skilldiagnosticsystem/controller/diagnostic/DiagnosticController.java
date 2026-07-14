package com.rafal.skilldiagnosticsystem.controller.diagnostic;

import com.rafal.skilldiagnosticsystem.dto.*;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/quiz")
@RestController
public class DiagnosticController {
    private final QuizService quizService;
    private final QuestionService questionService;
    private final QuizAttemptService quizAttemptService;

    public DiagnosticController(QuestionService questionService,
                                QuizService quizService,
                                QuizAttemptService quizAttemptService) {
        this.questionService = questionService;
        this.quizService = quizService;
        this.quizAttemptService = quizAttemptService;
    }

    @GetMapping("/questions")
    public List<QuestionResponseDto> getQuestions(@RequestParam Long subtopicId, @RequestParam DifficultyLevel difficulty) {
        return questionService.getQuestions(subtopicId, difficulty);
    }

    @GetMapping("/attempts")
    public long getAttempts(@RequestParam Long subtopicId, @RequestParam DifficultyLevel difficulty) {
        return quizAttemptService.getAttempts(subtopicId, difficulty);
    }

    @GetMapping("/history")
    public List<QuizResultDTO> getResultsHistory() {
        return quizService.getResultsHistory();
    }

    @PostMapping("/submit")
    public QuizResultDTO submitQuiz(@RequestBody QuizSubmissionRequest request) {
        return quizService.submitQuiz(request);
    }
}
