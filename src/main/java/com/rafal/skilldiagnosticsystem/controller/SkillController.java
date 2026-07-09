package com.rafal.skilldiagnosticsystem.controller;

import com.rafal.skilldiagnosticsystem.dto.*;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping("/quiz")
@RestController
public class SkillController {
    private final TopicService topicService;
    private final QuestionService questionService;
    private final QuizService quizService;
    private final QuizAttemptService quizAttemptService;
    private final SkillProgressService skillProgressService;

    public SkillController(TopicService topicService,
                           QuestionService questionService,
                           QuizService quizService, QuizAttemptService quizAttemptService,
                           SkillProgressService skillProgressService) {
        this.topicService = topicService;
        this.questionService = questionService;
        this.quizService = quizService;
        this.quizAttemptService = quizAttemptService;
        this.skillProgressService = skillProgressService;
    }

    // wyswietlenie tematow i podtematow z bazy
    @GetMapping("/topics")
    public List<TopicDTO> allTitleTopic() {
        return topicService.getAllTopicsAsDTO();
    }
    // zliczanie pytan wszystkich
    @GetMapping("/questionCount")
    public Long questionCount(@RequestParam Long subtopicId, @RequestParam DifficultyLevel diff) {
        return questionService.questionCount(subtopicId, diff);
    }


    // wszystkie pytania w bazie
    @GetMapping("/allQuestionCount")
    public Long allQuestion() {
        return questionService.allQuestions();
    }
    // zliczanie pytan konkretnej kategorii
    @GetMapping("/categoryQuestionCount")
    public Long categoryQuestion(@RequestParam String category) {
        return questionService.categoryQuestions(category);
    }
    // zliczanie podejsc kategorii
    @GetMapping("/attemptsCount")
    public long attempts(@RequestParam String category) {
        return quizAttemptService.attempts(category);
    }
//===================================================================

    // wyswietlenie konkretnych pytan do diagnostyki
    @GetMapping("/questionsToDiagnostic")
    public List<QuestionResponseDto> randomQuestions(@RequestParam Long subtopicId, @RequestParam DifficultyLevel difficulty) {
        return questionService.getRandomQuestions(subtopicId, difficulty);
    }
//==============================================================================
    // zliczanie prob diagnostyki
    @GetMapping("/attempts")
    public long attempts(@RequestParam Long subtopicId, @RequestParam DifficultyLevel difficulty) {
        return quizAttemptService.getAttempts(subtopicId, difficulty);
    }

    // wyniki po zrobieniu quizu
    @PostMapping("/userResults")
    public QuizResultDTO userAnswer(@RequestBody QuizSubmissionRequest quizSubmissionRequest) {
        return quizService.submitQuiz(quizSubmissionRequest);
    }


    // zwiekszanie pola mastery i sledzenie postepu
    @GetMapping("/progress")
    public SkillProgressDTO getProgress(@RequestParam Long subtopicId, @RequestParam DifficultyLevel difficulty) {
        return skillProgressService.getProgress(subtopicId, difficulty);
    }




    @GetMapping("/resultsHistory")
    public List<QuizResultDTO> resultsHistory() {
        return quizService.getResultsHistory();
    }

//    @GetMapping("/results")
//    public List<Double> readFromFile() {
//        return fileManagerService.readFromFile();
//    }
}
