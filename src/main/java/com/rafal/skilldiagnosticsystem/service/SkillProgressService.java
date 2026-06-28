package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.SkillProgressDTO;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.model.SkillProgress;
import com.rafal.skilldiagnosticsystem.repository.SkillProgressRepository;
import org.springframework.stereotype.Service;

@Service
public class SkillProgressService {
    private final SkillProgressRepository skillProgressRepository;

    public SkillProgressService(SkillProgressRepository skillProgressRepository) {
        this.skillProgressRepository = skillProgressRepository;
    }

    public SkillProgressDTO getProgress(Long subtopicId, DifficultyLevel difficulty) {
        SkillProgress progress =
                skillProgressRepository
                        .findBySubtopicIdAndDifficultyLevel(subtopicId, difficulty)
                        .orElse(null);

        if (progress == null) {
            return new SkillProgressDTO(0);
        }

        return new SkillProgressDTO(progress.getMastery());
    }
}
