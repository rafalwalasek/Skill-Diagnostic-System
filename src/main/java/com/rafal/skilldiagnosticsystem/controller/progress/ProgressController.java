package com.rafal.skilldiagnosticsystem.controller.progress;

import com.rafal.skilldiagnosticsystem.dto.SkillProgressDTO;
import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import com.rafal.skilldiagnosticsystem.service.SkillProgressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/quiz")
@RestController
public class ProgressController {
    private final SkillProgressService skillProgressService;

    public ProgressController(SkillProgressService skillProgressService) {
        this.skillProgressService = skillProgressService;
    }

    @GetMapping("/progress")
    public SkillProgressDTO getProgress(
            @RequestParam Long subtopicId,
            @RequestParam DifficultyLevel difficulty) {
        return skillProgressService.getProgress(subtopicId, difficulty);
    }
}
