package com.example.clog.models;

public class Post implements Posts {
    private long id;
    private String username;
    private String title;
    private String body;

    public Post(String username, String title, String body) {
        this.username = username;
        this.title = title;
        this.body = body;
    }

    public Post(long id, String username, String title, String body) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.body = body;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
