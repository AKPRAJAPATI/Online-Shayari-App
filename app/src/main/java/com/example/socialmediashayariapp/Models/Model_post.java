package com.example.socialmediashayariapp.Models;

public class Model_post {
    String imageUrl;
    String name;
    String shayari;
    String work;
    String postId;
    String postedBy;

    public Model_post() {
    }

    public Model_post(String imageUrl, String name, String shayari, String work, String postId, String postedBy) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.shayari = shayari;
        this.work = work;
        this.postId = postId;
        this.postedBy = postedBy;
    }

    public Model_post(String imageUrl, String name, String shayari, String work) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.shayari = shayari;
        this.work = work;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
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

    public String getShayari() {
        return shayari;
    }

    public void setShayari(String shayari) {
        this.shayari = shayari;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
