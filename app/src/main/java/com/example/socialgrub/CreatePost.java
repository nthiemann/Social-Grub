package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreatePost extends AppCompatActivity {

    String recipeTitle;
    String recipeDescription;

    EditText recipeTitleInput;
    EditText recipeDescriptionInput;
    Button postCancelButton;
    Button postRecipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        recipeTitleInput = (EditText) findViewById(R.id.recipeTitleInput);
        recipeDescriptionInput = (EditText) findViewById(R.id.descriptionBox);

        postRecipeButton = (Button) findViewById(R.id.postRecipeButton);
        postRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadPost();
            }
        });

        postCancelButton = (Button) findViewById(R.id.postCancelButton);
        postCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackToExploreAndCancel = new Intent(CreatePost.this, ExploreActivity.class);
                startActivity(goBackToExploreAndCancel);
            }
        });

        Spinner units = findViewById(R.id.measurementSpinner);
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(CreatePost.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.measurememnt));

        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units.setAdapter(unitAdapter);

    }

    private void uploadPost()
    {
        recipeTitle = recipeTitleInput.getText().toString();
        recipeDescription = recipeDescriptionInput.getText().toString();

        // Check for bad input
        if (recipeTitle.isEmpty())
        {
            recipeTitleInput.setError("Recipe title required");
            recipeTitleInput.requestFocus();
            return;
        }
        if (recipeDescription.isEmpty())
        {
            recipeDescriptionInput.setError("Please provide a brief description");
            recipeDescriptionInput.requestFocus();
            return;
        }

        // Here: create the recipe object under a user
    }
}