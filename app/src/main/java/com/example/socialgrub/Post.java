package com.example.socialgrub;

import android.util.EventLogTags;

import java.util.ArrayList;

public class Post {



    String recipeTitle;
    String recipeURL; //image

    //String username;
    //ArrayList<String> description;

    public Post() {
    }


    public Post(String recipeURL, String recipeTitle) {
        this.recipeURL = recipeURL;
        this.recipeTitle = recipeTitle;
        //this.username = username;
        //this.description = description;
    }

    public String getRecipeURL() {
        return recipeURL;
    }

    public void setRecipeURL(String recipeURL) {
        this.recipeURL = recipeURL;
    }

/*
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
*/

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }


/*
    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

*/



}
