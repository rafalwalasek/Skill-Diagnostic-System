package com.rafal.skilldiagnosticsystem.repository;

import com.rafal.skilldiagnosticsystem.model.Category;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    long countByCategoryAndDifficultyLevel(Category st, DifficultyLevel diff);
    List<Question> findByCategoryAndDifficultyLevel(Category st, DifficultyLevel diff);
}
