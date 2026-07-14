package com.rafal.skilldiagnosticsystem.mapper;

import com.rafal.skilldiagnosticsystem.dto.SubtopicDTO;
import com.rafal.skilldiagnosticsystem.dto.TopicDTO;
import com.rafal.skilldiagnosticsystem.model.Subtopic;
import com.rafal.skilldiagnosticsystem.model.Topic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicMapper {
    private final SubtopicMapper subtopicMapper;

    public TopicMapper(SubtopicMapper subtopicMapper) {
        this.subtopicMapper = subtopicMapper;
    }

    public TopicDTO toDto(Topic topic) {
        TopicDTO dto = new TopicDTO();
        dto.setId(topic.getId());
        dto.setTopicTitle(topic.getTopicTitle());

        List<SubtopicDTO> subtopicDtos = new ArrayList<>();
        for (Subtopic subtopic : topic.getSubtopics()) {
            subtopicDtos.add(subtopicMapper.toDto(subtopic));
        }

        dto.setSubtopicsDTOS(subtopicDtos);

        return dto;
    }
}
