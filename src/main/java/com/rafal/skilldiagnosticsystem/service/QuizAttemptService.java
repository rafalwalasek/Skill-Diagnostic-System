package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.repository.QuizAttemptRepository;
import org.springframework.stereotype.Service;

@Service
public class QuizAttemptService {
    private final QuizAttemptRepository quizAttemptRepository;

    public QuizAttemptService(QuizAttemptRepository quizAttemptRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
    }

    public long getAttempts(long subtopicId, DifficultyLevel difficulty) {
        return quizAttemptRepository.countBySubtopicIdAndDifficultyLevel(subtopicId, difficulty);
    }
    public long getCategoryAttempts(String category) {
        return quizAttemptRepository.countBySubtopic_Topic_TopicTitle(category);
    }
}
