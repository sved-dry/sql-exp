package com.sql.exp;

import javax.persistence.*;

@Entity
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String genre;

    public Topic() {

    }

    public Topic(Long id, String question, String genre) {
        this.id = id;
        this.question = question;
        this.genre = genre;
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
}
