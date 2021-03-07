package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class AddDirections extends AppCompatActivity {
    String direction1;
    String direction2;
    String additionalDirection;

    private EditText editDirection1;
    private EditText editDirection2;
    private EditText editAdditionalDirection;

    private Button addBothDirections;
    private Button addAdditionalDirection;
    private Button toTagsPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_directions);

        editDirection1 = findViewById(R.id.editTextDirection1);
        editDirection2 = findViewById(R.id.editTextDirection2);
        toTagsPage = findViewById(R.id.toAddTagsButton);
//        toTagsPage.setEnabled(false);
//        toTagsPage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openTagsPage();
//            }
//        });

        addBothDirections = findViewById(R.id.addBothDirectionsButton);
        addBothDirections.setEnabled(false);
        addBothDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction1 = editDirection1.getText().toString();
                direction2 = editDirection2.getText().toString();

                editDirection1.getText().clear();
                editDirection2.getText().clear();

                toTagsPage.setEnabled(true);
            }
        });

        editAdditionalDirection = findViewById(R.id.editTextAdditionalDirection);
        addAdditionalDirection = findViewById(R.id.additionalDirectionButton);
        addAdditionalDirection.setEnabled(false);
        addAdditionalDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additionalDirection = editAdditionalDirection.getText().toString();

                editAdditionalDirection.getText().clear();
            }
        });

        editDirection1.addTextChangedListener(addBothDirectionsTextWatcher);
        editDirection2.addTextChangedListener(addBothDirectionsTextWatcher);
        editAdditionalDirection.addTextChangedListener(addAdditionalDirectionTextWatcher);
    }

//    public void openTagsPage() {
//        Intent goToTagsPage = new Intent(this, AddTags.class);
//        startActivity(goToTagsPage);
//    }

    private TextWatcher addBothDirectionsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String direction1_input = editDirection1.getText().toString();
            String direction2_input = editDirection2.getText().toString();

            addBothDirections.setEnabled(!direction1_input.isEmpty() && !direction2_input.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher addAdditionalDirectionTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String additionlDirectionInput = editAdditionalDirection.getText().toString();
            addAdditionalDirection.setEnabled(!additionlDirectionInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}


