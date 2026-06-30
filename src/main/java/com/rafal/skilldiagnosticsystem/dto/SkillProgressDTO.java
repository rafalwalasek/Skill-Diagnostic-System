package com.rafal.skilldiagnosticsystem.dto;

import com.rafal.skilldiagnosticsystem.model.ProgressStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillProgressDTO {
    private int mastery;
    private ProgressStatus status;

    public SkillProgressDTO(int mastery) {
        this.mastery = mastery;
        this.status = ProgressStatus.fromPercentage(mastery);
    }
}
