package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String url;


    public void setName(String name) {
        this.name = name;
    }


    public Film(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Film() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Film(String name, String url) {
        this.name = name;
        this.url = url;
    }


    public Long getId() {
        return id;
    }
}
