package com.rafal.skilldiagnosticsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topicTitle;

    @OneToMany(mappedBy = "topic")
    private List<Subtopic> subtopics = new ArrayList<>(); //zabezpieczenie przed: NullPointerException
}
