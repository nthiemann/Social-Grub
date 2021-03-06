package com.example.socialgrub;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe
{
    public String recipeTitle;
    public String recipeDescription;
    public Uri uri;
    public String ImageURL;

    public String recipeTagOne;
    public String recipeTagTwo;
    public String recipeTagThree;

    public ArrayList<String> recipeIngredientList;
    public ArrayList<String> recipeExtraTags;
    public ArrayList<String> recipeMeasurements;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference getStoresRecipe = db.getReference("Image Dish");

    public Recipe() {

    }

    public void uploadRecipe()
    {
        HashMap<String,Object> attributeMap = new HashMap();
        /*
        attributeMap.put();
        attributeMap.put();
        attributeMap.put();
        attributeMap.put();
        attributeMap.put();
        attributeMap.put();
        */
        getStoresRecipe.push();
        String ID = getStoresRecipe.getKey();
        getStoresRecipe.child(ID).setValue(attributeMap);


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

    public void setRecipeIngredientList(ArrayList<String> list){
        this.recipeIngredientList = list;
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
