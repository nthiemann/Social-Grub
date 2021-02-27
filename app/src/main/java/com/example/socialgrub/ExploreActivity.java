package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
//import android.widget.Spinner;

public class ExploreActivity extends AppCompatActivity {

    ImageButton settingButton;
    ImageButton createPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        /*Spinner filter = (Spinner) findViewById(R.id.spinner2);

        // Container that holds the values to integrate with spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ExploreActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filters));

        // Make it a drop-down list
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(myAdapter);


        */

        settingButton = (ImageButton) findViewById(R.id.settings_button);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExploreActivity.this, SettingsActivity.class));
            }
        });

        createPostButton = (ImageButton) findViewById(R.id.createRecipePage);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreatePostActivityPage = new Intent(ExploreActivity.this, CreatePost.class);
                startActivity(goToCreatePostActivityPage);
            }
        });


    }
}