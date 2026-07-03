package com.rafal.skilldiagnosticsystem.repository;

import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    long countBySubtopicIdAndDifficultyLevel(Long subtopicId, DifficultyLevel diff);
    List<Question> findBySubtopicIdAndDifficultyLevel(Long subtopicId, DifficultyLevel difficulty);
    long countBySubtopic_Topic_TopicTitle(String category);
}
