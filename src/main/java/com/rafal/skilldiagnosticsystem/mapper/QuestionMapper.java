package com.rafal.skilldiagnosticsystem.mapper;

import com.rafal.skilldiagnosticsystem.dto.QuestionResponseDto;
import com.rafal.skilldiagnosticsystem.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    public QuestionResponseDto toDto(Question question) {
        QuestionResponseDto dto = new QuestionResponseDto();
        dto.setId(question.getId());
        dto.setContent(question.getContent());
        dto.setAnswerA(question.getAnswerA());
        dto.setAnswerB(question.getAnswerB());
        dto.setAnswerC(question.getAnswerC());
        dto.setAnswerD(question.getAnswerD());

        return dto;
    }
}
