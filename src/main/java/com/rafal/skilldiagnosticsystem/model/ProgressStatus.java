package com.rafal.skilldiagnosticsystem.model;

public enum ProgressStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED;

    public static ProgressStatus fromPercentage(int percentage) {
        if (percentage <= 0) {
            return NOT_STARTED;
        }
        if (percentage >= 100) {
            return COMPLETED;
        }
        return IN_PROGRESS;
    }
}
