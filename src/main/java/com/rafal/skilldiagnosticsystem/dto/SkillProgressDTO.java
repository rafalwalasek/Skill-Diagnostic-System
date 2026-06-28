package com.rafal.skilldiagnosticsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillProgressDTO {
    private int mastery;

    public SkillProgressDTO(int mastery) {
        this.mastery = mastery;
    }
}
