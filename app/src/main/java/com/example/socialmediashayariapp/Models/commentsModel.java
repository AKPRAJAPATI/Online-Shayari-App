package com.example.socialmediashayariapp.Models;

public class commentsModel {

    String imageUrl;
    String name;
    String postId;
    String postedBy;
    String myComments;

    public commentsModel() {
    }

    public commentsModel(String imageUrl, String name, String postId, String postedBy, String myComments) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.postId = postId;
        this.postedBy = postedBy;
        this.myComments = myComments;
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

    public String getMyComments() {
        return myComments;
    }

    public void setMyComments(String myComments) {
        this.myComments = myComments;
    }
}
