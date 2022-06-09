package com.example.socialmediashayariapp.Models;

public class Post {
    String imageUrl;
    String name;
    String work;
    int postLike;

    public Post() {
    }

    public Post(String imageUrl, String name, String work) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.work = work;
    }

    public int getPostLike() {
        return postLike;
    }

    public void setPostLike(int postLike) {
        this.postLike = postLike;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}

