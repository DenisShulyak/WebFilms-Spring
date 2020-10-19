package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    private Long film_id;
    private Long user_id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Comment(String message, Long film_id, Long user_id, String name) {
        this.message = message;
        this.film_id = film_id;
        this.user_id = user_id;
        this.name = name;
    }

    public Comment() {

    }

    public Comment(String message, Long film_id, Long user_id) {
        this.message = message;
        this.film_id = film_id;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public Long getUser_id() {
        return user_id;
    }
}
