package com.edhou.codefellowship.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    @ManyToOne
    ApplicationUser author;

    String body;
    Date createdAt;

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

    public Date getCreatedAt() {
        return createdAt;
    }
}
