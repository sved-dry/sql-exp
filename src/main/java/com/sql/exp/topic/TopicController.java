package com.sql.exp.topic;

import com.sql.exp.post.Post;
import com.sql.exp.post.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/v1/topic")
public class TopicController {

    private final TopicService topicService;

    private final PostService postService;

    private record PostResponse(String username,
                                String content) {}

    private record TopicResponse(Long id,
                                 String question,
                                 String genre,
                                 Integer hits,
                                 List<PostResponse> posts) {}

    public TopicController(TopicService topicService, PostService postService) {
        this.topicService = topicService;
        this.postService = postService;
    }

    @GetMapping(value = "")
    @ResponseBody
    public ResponseEntity<List<TopicResponse>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        List<TopicResponse> responses = new ArrayList<>();

        topics.forEach(topic ->  {
            List<Post> postsForTopic = postService.getPostsForTopic(topic.getId());

            List<PostResponse> postResponses = new ArrayList<>();
            postsForTopic.forEach(post -> {
                PostResponse postResponse = new PostResponse(post.getUsername(), post.getContent());
                postResponses.add(postResponse);
            });

            TopicResponse response = new TopicResponse(topic.getId(),
                    topic.getQuestion(),
                    topic.getGenre(),
                    topic.getHits(),
                    postResponses);
            responses.add(response);
        });


        return ResponseEntity.ok(responses);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<TopicResponse> getTopicById(@PathVariable String id) {
        Long idLong = Long.parseLong(id);
        Optional<Topic> topicOptional = topicService.getTopicById(idLong);

        if (topicOptional.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Topic topic = topicOptional.get();

        CompletableFuture.runAsync(() -> topicService.bumpHitCount(topic.getId()));

        topic.setHits(topic.getHits() + 1);

        List<Post> postsForTopic = postService.getPostsForTopic(idLong);

        List<PostResponse> postResponses = new ArrayList<>();
        postsForTopic.forEach(post -> {
            PostResponse postResponse = new PostResponse(post.getUsername(), post.getContent());
            postResponses.add(postResponse);
        });

        TopicResponse response = new TopicResponse(topic.getId(),
                topic.getQuestion(),
                topic.getGenre(),
                topic.getHits(),
                postResponses);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TopicResponse> createTopic(@RequestBody Topic incomingTopic) {

        if (incomingTopic == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (incomingTopic.getId() != null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        incomingTopic.setHits(1);

        Topic topicFromPersistence = topicService.createTopic(incomingTopic);

        TopicResponse response = new TopicResponse(topicFromPersistence.getId(),
                topicFromPersistence.getQuestion(),
                topicFromPersistence.getGenre(),
                topicFromPersistence.getHits(),
                Collections.emptyList());

        return ResponseEntity.ok(response);
    }
}
