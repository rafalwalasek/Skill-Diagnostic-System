package com.rafal.skilldiagnosticsystem.controller.topic;

import com.rafal.skilldiagnosticsystem.dto.TopicDTO;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/quiz")
@RestController
public class TopicController {
    private final TopicService topicService;
    private final QuestionService questionService;

    public TopicController(TopicService topicService,
                           QuestionService questionService) {
        this.topicService = topicService;
        this.questionService = questionService;
    }

    @GetMapping("/topics")
    public List<TopicDTO> getTopics() {
        return topicService.getAllTopics();
    }
    @GetMapping("/questionCount")
    public long getQuestionCount(@RequestParam Long subtopicId, @RequestParam DifficultyLevel difficulty) {
        return questionService.getQuestionCount(subtopicId, difficulty);
    }
}
