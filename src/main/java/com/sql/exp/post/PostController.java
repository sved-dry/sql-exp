package com.sql.exp.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "")
    @ResponseBody
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postService.getAllPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping(value = "/api/v1/topic/{topicId}/post",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Post> submitPost(@RequestBody Post post, @PathVariable  String topicId) {
        Long topicIdLong = Long.parseLong(topicId);

        Post savedPost = postService.createPost(post);

        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }
}
