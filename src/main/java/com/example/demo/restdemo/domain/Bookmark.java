package com.example.demo.restdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Bookmark implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public String uri;

    public String description;

    @JsonIgnore
    @ManyToOne
    private User account;


    Bookmark() {
    }

    public Bookmark(User account, String uri, String description) {
        this.uri = uri;
        this.description = description;
        this.account = account;
    }


    public User getAccount() {
        return account;
    }

    public Long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getDescription() {
        return description;
    }
}