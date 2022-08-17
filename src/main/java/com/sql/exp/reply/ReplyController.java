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

    @PostMapping(value = "/api/v1/topic/{topicId}/reply",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Reply> submitPost(@RequestBody Reply reply, @PathVariable  String topicId) {
        Long topicIdLong = Long.parseLong(topicId);

        Reply savedReply = replyService.createReply(reply);



        return new ResponseEntity<>(savedReply, HttpStatus.OK);
    }
}
