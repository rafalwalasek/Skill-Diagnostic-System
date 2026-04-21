package com.rafal.skilldiagnosticsystem;

public class Question {
    private final String question;
    private final String answerA;
    private final String answerB;
    private final String answerC;
    private final String answerD;
    private final String correctAnswer;

    public Question(String question,
                    String answerA,
                    String answerB,
                    String answerC,
                    String answerD,
                    String correctAnswer) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void showQuestion() {
        System.out.println(question + "\n" +
                "A) " + answerA + "\n" +
                "B) " + answerB + "\n" +
                "C) " + answerC + "\n" +
                "D) " + answerD);
    }
    public boolean isCorrect(String odp) {
        if (correctAnswer.equals(odp)) {
            return true;
        } else {
            return false;
        }
    }
}
