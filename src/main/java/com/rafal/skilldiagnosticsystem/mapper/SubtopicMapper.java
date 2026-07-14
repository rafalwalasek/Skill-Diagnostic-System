package com.rafal.skilldiagnosticsystem.mapper;

import com.rafal.skilldiagnosticsystem.dto.SubtopicDTO;
import com.rafal.skilldiagnosticsystem.model.Subtopic;
import org.springframework.stereotype.Component;

@Component
public class SubtopicMapper {
    public SubtopicDTO toDto(Subtopic subtopic) {
        SubtopicDTO dto = new SubtopicDTO();
        dto.setId(subtopic.getId());
        dto.setSubtopicTitle(subtopic.getSubtopicTitle());

        return dto;
    }
}
