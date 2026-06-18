package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.QuizResultDTO;
import com.rafal.skilldiagnosticsystem.dto.QuizSubmissionRequest;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizService {
    private final QuestionRepository questionRepository;

    public QuizService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuizResultDTO checkAnswers(QuizSubmissionRequest quizSubmissionRequest) {
        Map<Long, String> userAnswerMap  = quizSubmissionRequest.getUserAnswerMap();

        String subtopicName = "";
        String difficulty = "";

        int score = 0;
        int totalQuestions = userAnswerMap.size();
        for (Long questionId : userAnswerMap.keySet()) {
            String userAnswer = userAnswerMap.get(questionId);
            Optional<Question> questId = questionRepository.findById(questionId);
            Question question = questId.orElseThrow();

            if (userAnswer.equals(question.getCorrectAnswer())) {
                score++;
            }

            subtopicName = question.getSubtopic().getSubtopicTitle();
            difficulty = question.getDifficultyLevel().name();
        }

        QuizResultDTO result = new QuizResultDTO();
        result.setScore(score);
        result.setTotalQuestions(totalQuestions);
        result.setPercentage(percentage(score, totalQuestions));
        result.setDate(LocalDate.now().toString());
        result.setSubtopicName(subtopicName);
        result.setDifficulty(difficulty);

        return result;
    }
    private int percentage(int score, int totalQuestions) {
        if (totalQuestions == 0) {
            return 0;
        }
        return (score * 100) / totalQuestions;
    }
}
