package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        Spinner filter = (Spinner) findViewById(R.id.spinner2);

        // Container that holds the values to integrate with spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ExploreActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filters));

        // Make it a drop-down list
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(myAdapter);
    }
}