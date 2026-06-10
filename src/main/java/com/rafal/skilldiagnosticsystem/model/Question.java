package com.rafal.skilldiagnosticsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subtopic_id")
    private Subtopic subtopic;

    public boolean isCorrect(String odp) {
        return correctAnswer.equals(odp);
    }
}
