package com.rafal.skilldiagnosticsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class QuizSubmissionRequest {
    private Map<Long, String> userAnswerMap;
}
