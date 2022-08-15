package com.sql.exp;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> getAllTopics() {
        return repository.findAll();
    }
}
