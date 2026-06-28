package com.rafal.skilldiagnosticsystem.repository;

import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.model.SkillProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillProgressRepository extends JpaRepository<SkillProgress, Long> {
    Optional<SkillProgress> findBySubtopicIdAndDifficultyLevel(Long subtopicId, DifficultyLevel difficultyLevel);
}
