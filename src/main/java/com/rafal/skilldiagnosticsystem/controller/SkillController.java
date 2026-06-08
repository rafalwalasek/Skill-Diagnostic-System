package com.rafal.skilldiagnosticsystem.controller;

import com.rafal.skilldiagnosticsystem.dto.QuestionRequestDto;
import com.rafal.skilldiagnosticsystem.dto.QuestionResponseDto;
import com.rafal.skilldiagnosticsystem.dto.QuizSubmissionRequest;
import com.rafal.skilldiagnosticsystem.dto.TopicDTO;
import com.rafal.skilldiagnosticsystem.model.Category;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.model.Topic;
import com.rafal.skilldiagnosticsystem.service.FileManagerService;
import com.rafal.skilldiagnosticsystem.service.QuestionService;
import com.rafal.skilldiagnosticsystem.service.SkillAssessmentService;
import com.rafal.skilldiagnosticsystem.service.TopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping("/quiz")
@RestController
public class SkillController {
    private final TopicService topicService;
    private final QuestionService questionService;

    public SkillController(TopicService topicService,
                           QuestionService questionService) {
        this.topicService = topicService;
        this.questionService = questionService;
    }

    // wyswietlanie tematow i podtematow z bazy danych
    @GetMapping("/topics")
    public List<TopicDTO> getAllTopics() {
        return topicService.getAllTopicsAsDTO();
    }
    // zliczanie pytan
    @GetMapping("/questionCount")
    public Long questionCount(@RequestParam Category st, @RequestParam DifficultyLevel diff) {
        return questionService.questionCount(st, diff);
    }
    // wyswietlenie wszystkich pytan
    @GetMapping("/questions")
    public List<Question> randomQuestions(@RequestParam Category st, @RequestParam DifficultyLevel diff) {
        return questionService.getRandomQuestions(st, diff);
    }

    // dodawanie pytan do bazy
    @PostMapping("/questions")
    public QuestionResponseDto addQuestion(@RequestBody QuestionRequestDto dto) {
        return questionService.addQuestionToDB(dto);
    }


//    @PostMapping("/submit")
//    public double submitAnswers(@RequestBody QuizSubmissionRequest quizSubmissionRequest) {
//        List<Question> questions = questionRepository.findAll();
//
//        double result = skillAssessmentService.checkPerformance(quizSubmissionRequest, questions);
//        fileManagerService.writeToFile(List.of(result));
//
//        return result;
//    }
//    @GetMapping("/results")
//    public List<Double> readFromFile() {
//        return fileManagerService.readFromFile();
//    }
}
