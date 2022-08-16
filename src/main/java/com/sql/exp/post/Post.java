package com.sql.exp.post;

import javax.persistence.*;

@Entity
@Table(name = "post",
        indexes = @Index(columnList = "topicId"))
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long topicId;

    private String content;

    private String username;

    public Post() {

    }

    public Post(Long id, Long topicId, String content, String username) {
        this.id = id;
        this.topicId = topicId;
        this.content = content;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
