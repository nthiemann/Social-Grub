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

    ArrayList<Ingredient> ingredients;
    ArrayList<String> directions;
    String tag1;
    String tag2;
    String tag3;
    String recipeTitle;
    String recipeDescription;
    String recipeUrl;


    public Recipe () {


    }



    public Recipe(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    public Recipe(ArrayList<Ingredient> ingredients, ArrayList<String> directions) {

        this.ingredients = ingredients;
        this.directions = directions;
    }

    public Recipe(ArrayList<Ingredient> ingredients, ArrayList<String> directions, String tag1, String tag2, String tag3) {

        this.ingredients = ingredients;
        this.directions = directions;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
    }




    public Recipe(ArrayList<Ingredient> ingredients, ArrayList<String> directions, String tag1, String tag2, String tag3,
                  String recipeTitle, String recipeDescription, String recipeUrl) {

        this.ingredients = ingredients;
        this.directions = directions;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeUrl = recipeUrl;



    }


    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
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




    public ArrayList<String> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<String> directions) {
        this.directions = directions;
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