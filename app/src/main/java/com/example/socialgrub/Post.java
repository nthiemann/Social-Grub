package com.example.socialgrub;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.EventLogTags;

import java.util.ArrayList;

@org.parceler.Parcel
class Post implements Parcelable {

    String recipeTitle;
    String recipeURL; //image
    ArrayList<String> tags;
    String postID;

    String username;

    //ArrayList<String> description;

    public Post() {
    }


    public Post(String recipeURL, String recipeTitle) {
        this.recipeURL = recipeURL;
        this.recipeTitle = recipeTitle;
        //this.username = username;
        //this.description = description;
    }
    public Post(String postID, String recipeTitle, String recipeURL, ArrayList<String> tags, String username)
    {
        this.postID = postID;
        this.recipeTitle = recipeTitle;
        this.recipeURL = recipeURL;
        this.tags = tags;
        this.username = username;
    }

    protected Post(Parcel in) {
        recipeTitle = in.readString();
        recipeURL = in.readString();
        tags = in.createStringArrayList();
        postID = in.readString();
        username = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeTitle);
        dest.writeString(recipeURL);
        dest.writeStringList(tags);
        dest.writeString(postID);
        dest.writeString(username);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public String getRecipeURL() {
        return recipeURL;
    }

    public void setRecipeURL(String recipeURL) {
        this.recipeURL = recipeURL;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public ArrayList<String> getTags(){return tags;}

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID){this.postID = postID;}

/*
    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

*/



}
