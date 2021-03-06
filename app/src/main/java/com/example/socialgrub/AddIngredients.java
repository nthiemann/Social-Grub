package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class AddIngredients extends AppCompatActivity {

    Button continueRecipeButton;
    Recipe recipePost;
    ArrayList<String> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        Intent intent = getIntent();
        recipePost = (Recipe) intent.getSerializableExtra("recipePost");

        //HashMap<String,Object> recipeMap = (HashMap<String,Object>)intent.getSerializableExtra("recipeMap");

        continueRecipeButton = (Button) findViewById(R.id.continueRecipeButton);
        continueRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent directionsIntent = new Intent(AddIngredients.this,Directions.class);
                directionsIntent.putExtra("recipePost", (Parcelable) recipePost);
                startActivity(directionsIntent);
            }
        });

    }


    private void setIngredients()
    {
        recipePost.setRecipeIngredientList(ingredientList);
    }
}