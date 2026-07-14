package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.QuestionResponseDto;
import com.rafal.skilldiagnosticsystem.mapper.QuestionMapper;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

@Service
public class QuestionService {
    private static final int EASY_LIMIT = 3;
    private static final int MEDIUM_LIMIT = 5;
    private static final int HARD_LIMIT = 10;

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionService(QuestionRepository questionRepository,
                           QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public long getAllQuestionCount() {
        return questionRepository.count();
    }
    public long getCategoryQuestionCount(String category) {
        return questionRepository.countBySubtopic_Topic_TopicTitle(category);
    }
    public long getQuestionCount(long subtopicId,
                                 DifficultyLevel difficulty) {
        return questionRepository.countBySubtopicIdAndDifficultyLevel(subtopicId, difficulty);
    }
    public List<QuestionResponseDto> getQuestions(long subtopicId,
                                                  DifficultyLevel difficulty) {
        List<Question> questions = questionRepository.findBySubtopicIdAndDifficultyLevel(subtopicId, difficulty);
        Collections.shuffle(questions);

        int limit = min(getQuestionsLimit(difficulty), questions.size());

        return mapQuestionsToDto(questions.subList(0, limit));
    }
    private int getQuestionsLimit(DifficultyLevel difficulty) {
        return switch (difficulty) {
            case EASY -> EASY_LIMIT;
            case MEDIUM -> MEDIUM_LIMIT;
            case HARD -> HARD_LIMIT;
        };
    }
    private List<QuestionResponseDto> mapQuestionsToDto(List<Question> questions) {
        List<QuestionResponseDto> dtos = new ArrayList<>();

        for (Question question : questions) {
            dtos.add(questionMapper.toDto(question));
        }

        return dtos;
    }
}
