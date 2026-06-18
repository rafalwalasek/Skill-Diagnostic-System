package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.QuizResultDTO;
import com.rafal.skilldiagnosticsystem.dto.QuizSubmissionRequest;
import com.rafal.skilldiagnosticsystem.model.Question;
import com.rafal.skilldiagnosticsystem.model.QuizAttempt;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import com.rafal.skilldiagnosticsystem.repository.QuizAttemptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizService {
    private final QuestionRepository questionRepository;
    private final QuizAttemptRepository quizAttemptRepository;

    public QuizService(QuestionRepository questionRepository,
                       QuizAttemptRepository quizAttemptRepository) {
        this.questionRepository = questionRepository;
        this.quizAttemptRepository = quizAttemptRepository;
    }

    public QuizResultDTO submitQuiz(QuizSubmissionRequest quizSubmissionRequest) {
        Map<Long, String> userAnswerMap  = quizSubmissionRequest.getUserAnswerMap();

        Question firstQuestion = null;
        LocalDate today = LocalDate.now();

        int score = 0;
        int totalQuestions = userAnswerMap.size();
        for (Long questionId : userAnswerMap.keySet()) {
            String userAnswer = userAnswerMap.get(questionId);
            Optional<Question> questId = questionRepository.findById(questionId);
            Question question = questId.orElseThrow();

            if (firstQuestion == null) {
                firstQuestion = question;
            }

            if (userAnswer.equals(question.getCorrectAnswer())) {
                score++;
            }
        }
        if (firstQuestion == null) {
            throw new IllegalStateException("No questions found");
        }

        saveQuizAttempt(score, totalQuestions, today, firstQuestion);

        QuizResultDTO result = new QuizResultDTO();
        result.setScore(score);
        result.setTotalQuestions(totalQuestions);
        result.setPercentage(percentage(score, totalQuestions));
        result.setDate(today.toString());
        result.setSubtopicName(firstQuestion.getSubtopic().getSubtopicTitle());
        result.setDifficulty(firstQuestion.getDifficultyLevel().name());

        return result;
    }
    private int percentage(int score, int totalQuestions) {
        if (totalQuestions == 0) {
            return 0;
        }
        return (score * 100) / totalQuestions;
    }
    private void saveQuizAttempt(int score, int totalQuestions, LocalDate today, Question question) {
        QuizAttempt attempt = new QuizAttempt();
        attempt.setScore(score);
        attempt.setTotalQuestions(totalQuestions);
        attempt.setPercentage(percentage(score, totalQuestions));
        attempt.setCompletedAt(today);
        attempt.setDifficultyLevel(question.getDifficultyLevel());
        attempt.setSubtopic(question.getSubtopic());

        quizAttemptRepository.save(attempt);
    }
}
