package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.ResultDTO;
import com.rafal.skilldiagnosticsystem.dto.QuizSubmissionRequest;
import com.rafal.skilldiagnosticsystem.mapper.ResultMapper;
import com.rafal.skilldiagnosticsystem.model.*;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import com.rafal.skilldiagnosticsystem.repository.QuizAttemptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private final QuestionRepository questionRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final SkillProgressService skillProgressService;
    private final ResultMapper resultMapper;

    public QuizService(QuestionRepository questionRepository,
                       QuizAttemptRepository quizAttemptRepository,
                       SkillProgressService skillProgressService,
                       ResultMapper resultMapper) {
        this.questionRepository = questionRepository;
        this.quizAttemptRepository = quizAttemptRepository;
        this.skillProgressService = skillProgressService;
        this.resultMapper = resultMapper;
    }

    public List<ResultDTO> getResultsHistory() {
        List<QuizAttempt> attempts = quizAttemptRepository.findAll();
        List<ResultDTO> results = new ArrayList<>();

        for (QuizAttempt attempt : attempts) {
            results.add(resultMapper.toDto(attempt));
        }

        return results;
    }
    public ResultDTO submitQuiz(QuizSubmissionRequest quizSubmissionRequest) {
        Map<Long, String> userAnswerMap  = quizSubmissionRequest.getUserAnswerMap();

        Question quizQuestion = null;
        LocalDate completedAt = LocalDate.now();

        int score = 0;
        int totalQuestions = userAnswerMap.size();
        for (Long questionId : userAnswerMap.keySet()) {
            String userAnswer = userAnswerMap.get(questionId);
            Question question = questionRepository
                    .findById(questionId)
                    .orElseThrow();

            if (quizQuestion == null) {
                quizQuestion = question;
            }

            if (userAnswer.equals(question.getCorrectAnswer())) {
                score++;
            }
        }
        if (quizQuestion == null) {
            throw new IllegalStateException("No questions found");
        }

        saveQuizAttempt(score, totalQuestions, completedAt, quizQuestion);

        return resultMapper.toQuizResult(
                score,
                totalQuestions,
                calculatePercentage(score, totalQuestions),
                completedAt,
                quizQuestion
        );
    }

    private int calculatePercentage(int score, int totalQuestions) {
        if (totalQuestions == 0) {
            return 0;
        }
        return (score * 100) / totalQuestions;
    }
    private void saveQuizAttempt(int score,
                                 int totalQuestions,
                                 LocalDate completedAt,
                                 Question quizQuestion) {
        int percentage = calculatePercentage(score, totalQuestions);

        QuizAttempt attempt = new QuizAttempt();
        attempt.setScore(score);
        attempt.setTotalQuestions(totalQuestions);
        attempt.setPercentage(percentage);
        attempt.setCompletedAt(completedAt);
        attempt.setDifficultyLevel(quizQuestion.getDifficultyLevel());
        attempt.setSubtopic(quizQuestion.getSubtopic());

        quizAttemptRepository.save(attempt);

        int masteryChange = calculateMasteryChange(quizQuestion.getDifficultyLevel(), score);

        skillProgressService.updateProgress(
                quizQuestion.getSubtopic(),
                quizQuestion.getDifficultyLevel(),
                masteryChange
        );
    }
    private int calculateMasteryChange(DifficultyLevel difficulty, int score) {
        return switch (difficulty) {
            case EASY -> switch (score) {
                case 3 -> 2;
                case 2 -> 1;
                case 1 -> 0;
                case 0 -> -1;
                default -> 0;
            };
            case MEDIUM -> switch (score) {
                case 5 -> 3;
                case 4 -> 2;
                case 3 -> 1;
                case 2 -> 0;
                case 1 -> -1;
                case 0 -> -2;
                default -> 0;
            };
            case HARD -> switch (score) {
                case 10 -> 5;
                case 9 -> 4;
                case 8 -> 3;
                case 7 -> 2;
                case 6 -> 1;
                case 5 -> 0;
                case 4 -> -1;
                case 3 -> -2;
                case 2 -> -3;
                case 1 -> -4;
                case 0 -> -5;
                default -> 0;
            };
        };
    }
}
