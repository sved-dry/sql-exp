package com.sql.exp.reply;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reply")
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping(value = "")
    @ResponseBody
    public ResponseEntity<List<Reply>> getReplies() {
        List<Reply> replies = replyService.getAllReplies();

        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    @PostMapping(value = "/topic/{topicId}",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Reply> submitReply(@RequestBody Reply reply, @PathVariable  String topicId) {
        Long topicIdLong = Long.parseLong(topicId);

        reply.setTopicId(topicIdLong);

        // TODO make users, use bogus long value here for now
        reply.setUserId(5L);

        Reply savedReply = replyService.createReply(reply);



        return new ResponseEntity<>(savedReply, HttpStatus.OK);
    }
}
