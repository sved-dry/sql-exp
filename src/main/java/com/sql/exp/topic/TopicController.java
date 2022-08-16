package com.sql.exp.topic;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/v1/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping(value = "")
    @ResponseBody
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();

        return ResponseEntity.ok(topics);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Topic> getTopicById(@PathVariable String id) {
        Long idLong = Long.parseLong(id);
        Optional<Topic> topicOptional = topicService.getTopicById(idLong);

        if (!topicOptional.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Topic topic = topicOptional.get();

        CompletableFuture.runAsync(() -> topicService.bumpHitCount(topic.getId()));

        topic.setHits(topic.getHits() + 1);

        return ResponseEntity.ok(topicOptional.get());
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Topic> createTopic(@RequestBody Topic incomingTopic) {

        if (incomingTopic == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (incomingTopic.getId() != null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        incomingTopic.setHits(1);

        Topic topicFromPersistence = topicService.createTopic(incomingTopic);

        return ResponseEntity.ok(topicFromPersistence);
    }
}
