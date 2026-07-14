package com.rafal.skilldiagnosticsystem.service;

import com.rafal.skilldiagnosticsystem.dto.TopicDTO;
import com.rafal.skilldiagnosticsystem.mapper.TopicMapper;
import com.rafal.skilldiagnosticsystem.model.Topic;
import com.rafal.skilldiagnosticsystem.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public TopicService(TopicRepository topicRepository,
                        TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    public List<TopicDTO> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();

        List<TopicDTO> topicDtos = new ArrayList<>();
        for (Topic topic : topics) {
            topicDtos.add(topicMapper.toDto(topic));
        }

        return topicDtos;
    }
}
