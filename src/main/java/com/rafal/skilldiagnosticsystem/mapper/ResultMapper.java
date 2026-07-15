package com.rafal.skilldiagnosticsystem.mapper;

import com.rafal.skilldiagnosticsystem.dto.ResultDTO;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.model.QuizAttempt;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ResultMapper {
    public ResultDTO toDto(QuizAttempt attempt) {
        return new ResultDTO(
                attempt.getId(),
                attempt.getScore(),
                attempt.getTotalQuestions(),
                attempt.getPercentage(),
                attempt.getCompletedAt(),
                attempt.getDifficultyLevel(),
                attempt.getSubtopic().getSubtopicTitle()
        );
    }
    public ResultDTO toQuizResult(int score,
                                  int totalQuestions,
                                  int percentage,
                                  LocalDate date,
                                  Question question) {
        ResultDTO dto = new ResultDTO();
        dto.setScore(score);
        dto.setTotalQuestions(totalQuestions);
        dto.setPercentage(percentage);
        dto.setDate(date);
        dto.setSubtopicName(question.getSubtopic().getSubtopicTitle());
        dto.setDifficulty(question.getDifficultyLevel());

        return dto;
    }
}
