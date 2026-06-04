package com.rafal.skilldiagnosticsystem.repository;

import com.rafal.skilldiagnosticsystem.model.Category;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    long countByCategoryAndDifficultyLevel(Category st, DifficultyLevel diff);
}
