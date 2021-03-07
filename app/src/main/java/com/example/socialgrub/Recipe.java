package com.example.socialgrub;

import android.net.Uri;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.HashMap;

@Parcel
public class Recipe {



    String ingredient1;



    String direction1;
    String tag1;
    String tag2;
    String tag3;
    String recipeTitle;
    String recipeDescription;
    String recipeUrl;


    public Recipe () {


    }

    public Recipe(String ingredient1) {

        this.ingredient1 = ingredient1;

    }


    public Recipe(String ingredient1, String direction1) {

        this.ingredient1 = ingredient1;
        this.direction1 = direction1;

    }


    public Recipe(String ingredient1, String direction1, String tag1, String tag2, String tag3 ) {


        this.ingredient1 = ingredient1;
        this.direction1 = direction1;
        this.tag1 = tag1;
        this.tag2 = tag3;
        this.tag2 = tag3;

    }

    public Recipe(String ingredient1, String direction1, String tag1, String tag2, String tag3,
                  String recipeTitle, String recipeDescription, String recipeUrl) {

        this.ingredient1 = ingredient1;
        this.direction1 = direction1;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeUrl = recipeUrl;



    }





    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }




    public String getDirection1() {
        return direction1;
    }

    public void setDirection1(String direction1) {
        this.direction1 = direction1;
    }

    public String getRecipeTagOne() {
        return tag1;
    }

    public String getRecipeTagTwo() {
        return tag2;
    }

    public String getRecipeTagThree() {
        return tag3;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
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

    public String getRecipeUrl() {
        return recipeUrl;
    }

    public void setRecipeUrl(String recipeUrl) {
        this.recipeUrl = recipeUrl;
    }
}