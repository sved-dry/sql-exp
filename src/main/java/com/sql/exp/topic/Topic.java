package com.sql.exp.topic;

import javax.persistence.*;

@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String genre;
    private Integer hits;

    public Topic() {

    }

    public Topic(Long id, String question, String genre, Integer hits) {
        this.id = id;
        this.question = question;
        this.genre = genre;
        this.hits = hits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String name) {
        this.question = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String country) {
        this.genre = country;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }
}
