package com.rafal.skilldiagnosticsystem.repository;

import com.rafal.skilldiagnosticsystem.model.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {}
