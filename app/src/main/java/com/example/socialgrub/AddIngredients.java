package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;

public class AddIngredients extends AppCompatActivity {

    Button continueRecipeButton;
    Recipe recipePost;
    //ArrayList<String> ingredientList; just to test

    EditText ingredientBox1;

    String recipeIngredient1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        /*
        Intent intent = getIntent();
        recipePost = (Recipe) intent.getSerializableExtra("recipePost");
        */

        continueRecipeButton = (Button) findViewById(R.id.continueButton);
        continueRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                if(enterIngredients()) {
                    Intent directionsIntent = new Intent(AddIngredients.this, AddDirections.class);
                    directionsIntent.putExtra("recipePost", (Parcelable) recipePost);


                    startActivity(directionsIntent);
                }
                */

                if(enterIngredients()) {


                    Intent directionsIntent = new Intent(AddIngredients.this, AddDirections.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("recipePost", Parcels.wrap(recipePost));
                    directionsIntent.putExtras(bundle);
                    startActivity(directionsIntent);
                }

            }
        });



        ingredientBox1 = (EditText) findViewById(R.id.IngredientBox1ID);






    }

    private boolean enterIngredients() {
        recipeIngredient1 = ingredientBox1.getText().toString();

        if (recipeIngredient1.isEmpty()) {
            ingredientBox1.setError("Recipe ingredients required");
            ingredientBox1.requestFocus();
            return false;
        }

        else {

            recipePost = new Recipe();
            //recipePost.setIngredient1(recipeIngredient1);
        }

        return true;

    }


    //private void setIngredients(){recipePost.setRecipeIngredientList(ingredientList);}
}