package com.example.demo.restdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BOOKMARK")
public class Bookmark implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "URI")
    public String uri;

    @Column(name = "DESCRIPTION")
    public String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USER_ID")
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