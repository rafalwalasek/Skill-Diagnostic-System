package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.QuestionResponseDto;
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
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Long questionCount(Long subtopicId, DifficultyLevel diff) {
        return questionRepository.countBySubtopicIdAndDifficultyLevel(subtopicId, diff);
    }
    public List<QuestionResponseDto> getRandomQuestions(Long subtopicId, DifficultyLevel difficulty) {
        List<Question> questions = questionRepository.findBySubtopicIdAndDifficultyLevel(subtopicId, difficulty);
        Collections.shuffle(questions);

        int limit;
        if (difficulty == DifficultyLevel.EASY) {
            limit = 3;
        } else if (difficulty == DifficultyLevel.MEDIUM) {
            limit = 5;
        } else {
            limit = 10;
        }

        return mapQuestionsToDto(questions.subList(0, min(limit, questions.size())));
    }
    private List<QuestionResponseDto> mapQuestionsToDto(List<Question> questions) {
        List<QuestionResponseDto> questionResponseDtoList = new ArrayList<>();

        for (Question question : questions) {
            QuestionResponseDto questionResponseDto = new QuestionResponseDto();
            questionResponseDto.setId(question.getId());
            questionResponseDto.setContent(question.getContent());
            questionResponseDto.setAnswerA(question.getAnswerA());
            questionResponseDto.setAnswerB(question.getAnswerB());
            questionResponseDto.setAnswerC(question.getAnswerC());
            questionResponseDto.setAnswerD(question.getAnswerD());

            questionResponseDtoList.add(questionResponseDto);
        }

        return questionResponseDtoList;
    }
}
