package com.rafal.skilldiagnosticsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TopicDTO {
    private Long id;
    private String topicTitle;
    private List<SubtopicDTO> subtopicsDTOS;
}
