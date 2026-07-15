package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.SkillProgressDTO;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.model.SkillProgress;
import com.rafal.skilldiagnosticsystem.model.Subtopic;
import com.rafal.skilldiagnosticsystem.repository.SkillProgressRepository;
import org.springframework.stereotype.Service;

@Service
public class SkillProgressService {
    private final SkillProgressRepository skillProgressRepository;

    public SkillProgressService(SkillProgressRepository skillProgressRepository) {
        this.skillProgressRepository = skillProgressRepository;
    }

    public SkillProgressDTO getProgress(long subtopicId, DifficultyLevel difficulty) {
        SkillProgress progress =
                skillProgressRepository
                        .findBySubtopicIdAndDifficultyLevel(subtopicId, difficulty)
                        .orElse(null);

        if (progress == null) {
            return new SkillProgressDTO(0);
        }

        return new SkillProgressDTO(progress.getMastery());
    }
    public void updateProgress(Subtopic subtopic,
                               DifficultyLevel difficultyLevel,
                               int change) {
        SkillProgress progress = skillProgressRepository
                .findBySubtopicIdAndDifficultyLevel(subtopic.getId(), difficultyLevel)
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
