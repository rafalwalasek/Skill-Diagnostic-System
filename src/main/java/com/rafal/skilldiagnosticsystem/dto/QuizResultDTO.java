package com.rafal.skilldiagnosticsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizResultDTO {
    private int score;
    private int totalQuestions;
    private int percentage;
    private String date;
    private String subtopicName;
    private String difficulty;
}
