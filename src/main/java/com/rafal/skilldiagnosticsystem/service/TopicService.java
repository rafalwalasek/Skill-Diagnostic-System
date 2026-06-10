package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.SubtopicDTO;
import com.rafal.skilldiagnosticsystem.dto.TopicDTO;
import com.rafal.skilldiagnosticsystem.model.Subtopic;
import com.rafal.skilldiagnosticsystem.model.Topic;
import com.rafal.skilldiagnosticsystem.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<TopicDTO> getAllTopicsAsDTO() {
        List<Topic> topics = topicRepository.findAll();

        List<TopicDTO> topicDTOS = new ArrayList<>();
        for (Topic topic : topics) {
            TopicDTO dto = new TopicDTO();
            dto.setId(topic.getId());
            dto.setTopicTitle(topic.getTopicTitle());

            List<SubtopicDTO> subtopicDTOS = new ArrayList<>();
            for (Subtopic subtopic : topic.getSubtopics()) {
                SubtopicDTO subDto = new SubtopicDTO();
                subDto.setId(subtopic.getId());
                subDto.setSubtopicTitle(subtopic.getSubtopicTitle());

                subtopicDTOS.add(subDto);
            }

            dto.setSubtopicsDTOS(subtopicDTOS);
            topicDTOS.add(dto);
        }

        return topicDTOS;
    }
}
