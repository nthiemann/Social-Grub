package com.example.socialgrub;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Recipe
{

    //posting step 1
    public String recipeTitle;
    public String recipeDescription;
    public String ImageURL;
    public String postID;

 /* public String recipeTagOne;
    public String recipeTagTwo;
    public String recipeTagThree;

    public ArrayList<String> recipeIngredientList;
    public ArrayList<String> recipeExtraTags;
    public ArrayList<String> recipeMeasurements;
*/



    //post step 3 directions
    public String recipeDirection1;
    public String recipeDirection2;
    public String recipeDirection3;
    public String recipeDirection4;
    public String recipeDirection5;



    //post step 4 Tags
    public String tag1;















    public Recipe() {




    }


    public Recipe(String recipeTitle, String recipeDescription, String imageURL, String postID) {

        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.ImageURL = imageURL;
        this.postID = postID;
    }


    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }



    public String getRecipeDirection1() {
        return recipeDirection1;
    }

    public void setRecipeDirection1(String recipeDirection1) {
        this.recipeDirection1 = recipeDirection1;
    }




    public String getRecipeDirection2() {
        return recipeDirection2;
    }

    public void setRecipeDirection2(String recipeDirection2) {
        this.recipeDirection2 = recipeDirection2;
    }



    public String getRecipeDirection3() {
        return recipeDirection3;
    }

    public void setRecipeDirection3(String recipeDirection3) {
        this.recipeDirection3 = recipeDirection3;
    }



    public String getRecipeDirection4() {
        return recipeDirection4;
    }

    public void setRecipeDirection4(String recipeDirection4) {
        this.recipeDirection4 = recipeDirection4;
    }



    public String getRecipeDirection5() {
        return recipeDirection5;
    }

    public void setRecipeDirection5(String recipeDirection5) {
        this.recipeDirection5 = recipeDirection5;
    }




















    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }




    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

/*
    public String getRecipeTagOne() {
        return recipeTagOne;
    }

    public void setRecipeTagOne(String recipeTagOne) {
        this.recipeTagOne = recipeTagOne;
    }

    public String getRecipeTagTwo() {
        return recipeTagTwo;
    }

    public void setRecipeTagTwo(String recipeTagTwo) {
        this.recipeTagTwo = recipeTagTwo;
    }

    public String getRecipeTagThree() {
        return recipeTagThree;
    }

    public void setRecipeTagThree(String recipeTagThree) {
        this.recipeTagThree = recipeTagThree;
    }

    public ArrayList<String> getRecipeIngredientList() {
        return recipeIngredientList;
    }

    public void setRecipeIngredientList(ArrayList<String> recipeIngredientList) {
        this.recipeIngredientList = recipeIngredientList;
    }

    public ArrayList<String> getRecipeExtraTags() {
        return recipeExtraTags;
    }

    public void setRecipeExtraTags(ArrayList<String> recipeExtraTags) {
        this.recipeExtraTags = recipeExtraTags;
    }

    public ArrayList<String> getRecipeMeasurements() {
        return recipeMeasurements;
    }

    public void setRecipeMeasurements(ArrayList<String> recipeMeasurements) {
        this.recipeMeasurements = recipeMeasurements;
    }

    */
}
