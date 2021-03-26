package com.example.socialgrub;

public class PostRating {

    String userID;
    float rating;

    public PostRating(String userID, float rating) {
        this.userID = userID;
        this.rating = rating;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
