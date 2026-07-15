package com.rafal.skilldiagnosticsystem.dto;

import com.rafal.skilldiagnosticsystem.model.DifficultyLevel;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    private long id;
    private int score;
    private int totalQuestions;
    private int percentage;
    private LocalDate date;
    private DifficultyLevel difficulty;
    private String subtopicName;
}
