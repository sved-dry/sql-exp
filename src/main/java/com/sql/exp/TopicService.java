package com.sql.exp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> getAllTopics() {
        return repository.findAll();
    }

    public Topic createTopic(Topic incomingTopic) {
        return repository.save(incomingTopic);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void bumpHitCount(Long id) {
        Objects.requireNonNull(id, "id was null");

        Topic deltaTopic = getTopicById(id)
                .orElseThrow(() -> new IllegalStateException((String.format("Topic by id %d was not found.", id))));

        deltaTopic.setHits(deltaTopic.getHits() + 1);

        repository.save(deltaTopic);
    }

    public Optional<Topic> getTopicById(Long id) {
        return repository.findTopicById(id);
    }
}
