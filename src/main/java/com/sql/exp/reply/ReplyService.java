package com.sql.exp.reply;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {

    private final ReplyRepository repository;

    public ReplyService(ReplyRepository repository) {
        this.repository = repository;
    }

    public List<Reply> getAllReplies() {
        return repository.findAll();
    }

    public List<Reply> getRepliesForTopic(Long topicId) {
        return repository.findRepliesTopicId(topicId);

    }
    public Reply createReply(Reply incomingReply) {
        return repository.save(incomingReply);
    }

    public Optional<Reply> getReplyById(Long id) {
        return repository.findReplyById(id);
    }
}
