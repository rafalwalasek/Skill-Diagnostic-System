package com.rafal.skilldiagnosticsystem.model;

import jakarta.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Question() {}
    public Question(String question,
                    String answerA,
                    String answerB,
                    String answerC,
                    String answerD,
                    String correctAnswer,
                    Category category) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
        this.category = category;
    }

    public Long getId() {
        return id;
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswerA() {
        return answerA;
    }
    public String getAnswerB() {
        return answerB;
    }
    public String getAnswerC() {
        return answerC;
    }
    public String getAnswerD() {
        return answerD;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public Category getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }
    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }
    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }
    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isCorrect(String odp) {
        return correctAnswer.equals(odp);
    }
}
