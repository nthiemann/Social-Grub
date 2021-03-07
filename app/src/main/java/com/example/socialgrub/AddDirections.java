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
    private EditText editDirection1;
    private EditText editDirection2;
    private EditText editAdditionalDirection;

    private Button addBothDirections;
    private Button addAdditionalDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_directions);

        editDirection1 = findViewById(R.id.editTextDirection1);
        editDirection2 = findViewById(R.id.editTextDirection2);
        addBothDirections = findViewById(R.id.addBothDirectionsButton);

        editAdditionalDirection = findViewById(R.id.editTextAdditionalDirection);
        addAdditionalDirection = findViewById(R.id.additionalDirectionButton);

        editDirection1.addTextChangedListener(addBothDirectionsTextWatcher);
        editDirection2.addTextChangedListener(addBothDirectionsTextWatcher);
        editAdditionalDirection.addTextChangedListener(addAdditionalDirectionTextWatcher);
    }

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


