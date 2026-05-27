package com.rafal.skilldiagnosticsystem.dto;

import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequestDto {
    private String content;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private DifficultyLevel difficulty;
    private Long categoryId;
}
