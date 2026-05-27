package com.rafal.skilldiagnosticsystem.dto;

import java.util.List;

public class QuizSubmissionRequest {
    private List<UserAnswer> userAnswerList;

    public List<UserAnswer> getUserAnswerList() {
        return userAnswerList;
    }

    public void setUserAnswerList(List<UserAnswer> userAnswerList) {
        this.userAnswerList = userAnswerList;
    }
}
