package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.repository.QuizAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizAttemptService {
    private final QuizAttemptRepository quizAttemptRepository;

    public long getAttempts(Long subtopicId, DifficultyLevel difficultyLevel) {
        return quizAttemptRepository.countBySubtopicIdAndDifficultyLevel(subtopicId, difficultyLevel);
    }
}
