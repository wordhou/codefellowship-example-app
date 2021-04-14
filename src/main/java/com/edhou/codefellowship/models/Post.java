package com.edhou.codefellowship.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    public long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    @ManyToOne
    ApplicationUser author;

    String body;
    Date createdAt;

    public Post() {
    }

    public Post(ApplicationUser author, String body) {
        this.author = author;
        this.body = body;
        this.createdAt = new Date();
    }

    public ApplicationUser getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public String getShortBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
