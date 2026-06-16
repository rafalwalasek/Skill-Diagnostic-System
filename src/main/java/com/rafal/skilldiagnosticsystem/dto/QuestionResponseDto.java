package com.rafal.skilldiagnosticsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponseDto {
    private Long id;
    private String content;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
}
