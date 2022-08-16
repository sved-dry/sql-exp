package com.sql.exp.post;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post createPost(Post incomingPost) {
        return repository.save(incomingPost);
    }

    public Optional<Post> getPostById(Long id) {
        return repository.findPostById(id);
    }
}
