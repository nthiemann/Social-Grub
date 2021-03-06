package com.example.socialgrub;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe implements Parcelable
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

    protected Recipe(Parcel in) {
        recipeTitle = in.readString();
        recipeDescription = in.readString();
        uri = in.readParcelable(Uri.class.getClassLoader());
        imageURL = in.readString();
        direction1 = in.readString();
        recipeTagOne = in.readString();
        recipeTagTwo = in.readString();
        recipeTagThree = in.readString();
        recipeIngredientList = in.createStringArrayList();
        recipeExtraTags = in.createStringArrayList();
        recipeMeasurements = in.createStringArrayList();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

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


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public  void writeToParcel(Parcel parcel, int i)
    {

        parcel.writeString(recipeTitle);
        parcel.writeString(recipeDescription);
        parcel.writeParcelable(uri, i);
        parcel.writeString(imageURL);
        parcel.writeString(direction1);
        parcel.writeString(recipeTagOne);
        parcel.writeString(recipeTagTwo);
        parcel.writeString(recipeTagThree);
        parcel.writeStringList(recipeIngredientList);
        parcel.writeStringList(recipeExtraTags);
        parcel.writeStringList(recipeMeasurements);
    }

}
