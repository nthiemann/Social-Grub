package com.example.socialgrub;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe
{
    private String recipeTitle;
    private String recipeDescription;
    private Uri uri;
    private String imageURL;

    private String direction1;

    private String recipeTagOne;
    private String recipeTagTwo;
    private String recipeTagThree;

    private ArrayList<String> recipeIngredientList;
    private ArrayList<String> recipeExtraTags;
    private ArrayList<String> recipeMeasurements;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference getStoresRecipe = db.getReference("Image Dish");

    public Recipe() {

    }

    public void uploadRecipe()
    {
        HashMap<String,Object> attributeMap = new HashMap();

        attributeMap.put("title", recipeTitle);
        attributeMap.put("imageURL", imageURL);
        attributeMap.put("description", recipeDescription);
        //attributeMap.put();
        //attributeMap.put();
        //attributeMap.put();

        getStoresRecipe.push();
        String ID = getStoresRecipe.getKey();
        getStoresRecipe.child(ID).setValue(attributeMap);

        // store ID under user here


    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        imageURL = imageURL;
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

    public String getRecipeTagOne() {
        return recipeTagOne;
    }

    public void setTag1(String recipeTagOne) {
        this.recipeTagOne = recipeTagOne;
    }

    public String getRecipeTagTwo() {
        return recipeTagTwo;
    }

    public void setTag2(String recipeTagTwo) {
        this.recipeTagTwo = recipeTagTwo;
    }

    public String getRecipeTagThree() {
        return recipeTagThree;
    }

    public void setTag3(String recipeTagThree) {
        this.recipeTagThree = recipeTagThree;
    }

    public ArrayList<String> getRecipeIngredientList() {
        return recipeIngredientList;
    }

    public ArrayList<String> getRecipeExtraTags() {
        return recipeExtraTags;
    }

    public void setRecipeDirection1(String direction)
    {
        this.direction1 = direction;
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


}
