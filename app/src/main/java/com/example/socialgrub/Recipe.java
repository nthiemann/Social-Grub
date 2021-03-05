package com.example.socialgrub;

import java.util.ArrayList;

public class Recipe
{
    public String recipeTitle;
    public String recipeDescription;
    public String recipeTagOne;
    public String recipeTagTwo;
    public String recipeTagThree;
    public ArrayList<String> recipeIngredientList;
    public ArrayList<String> recipeExtraTags;
    public ArrayList<String> recipeMeasurements;

    public Recipe(String recipeTitle, String recipeDescription, String recipeTagOne,
                  String recipeTagTwo, String recipeTagThree,
                  ArrayList<String> recipeIngredientList, ArrayList<String> recipeExtraTags,
                  ArrayList<String> recipeMeasurements) {
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeTagOne = recipeTagOne;
        this.recipeTagTwo = recipeTagTwo;
        this.recipeTagThree = recipeTagThree;
        this.recipeIngredientList = recipeIngredientList;
        this.recipeExtraTags = recipeExtraTags;
        this.recipeMeasurements = recipeMeasurements;
    }
    public Recipe(String imageURI)
    {
        ;
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
}
