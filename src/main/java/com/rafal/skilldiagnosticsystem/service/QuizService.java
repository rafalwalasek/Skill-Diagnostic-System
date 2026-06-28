package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.QuizResultDTO;
import com.rafal.skilldiagnosticsystem.dto.QuizSubmissionRequest;
import com.rafal.skilldiagnosticsystem.model.*;
import com.rafal.skilldiagnosticsystem.repository.QuestionRepository;
import com.rafal.skilldiagnosticsystem.repository.QuizAttemptRepository;
import com.rafal.skilldiagnosticsystem.repository.SkillProgressRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizService {
    private final QuestionRepository questionRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final SkillProgressRepository skillProgressRepository;

    public QuizService(QuestionRepository questionRepository,
                       QuizAttemptRepository quizAttemptRepository,
                       SkillProgressRepository skillProgressRepository) {
        this.questionRepository = questionRepository;
        this.quizAttemptRepository = quizAttemptRepository;
        this.skillProgressRepository = skillProgressRepository;
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

        int masteryChange = calculateMasteryChange(question.getDifficultyLevel(), score);
        updateSkillProgress(question.getSubtopic(), question.getDifficultyLevel(), masteryChange);
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
    private void updateSkillProgress(Subtopic subtopic, DifficultyLevel difficultyLevel, int change) {
        SkillProgress progress = skillProgressRepository.findBySubtopicIdAndDifficultyLevel(subtopic.getId(), difficultyLevel)
                .orElseGet(() -> {
                    SkillProgress newProgress = new SkillProgress();
                    newProgress.setSubtopic(subtopic);
                    newProgress.setDifficultyLevel(difficultyLevel);
                    newProgress.setMastery(0);

                    return newProgress;
                });

        int newMastery = progress.getMastery() + change;
        newMastery = Math.max(0, Math.min(100, newMastery));

        progress.setMastery(newMastery);

        skillProgressRepository.save(progress);
    }
}
