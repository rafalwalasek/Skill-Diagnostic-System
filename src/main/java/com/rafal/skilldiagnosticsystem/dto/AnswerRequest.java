package com.rafal.skilldiagnosticsystem.dto;

import java.util.List;

public class AnswerRequest {
    private List<String> answers;

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
