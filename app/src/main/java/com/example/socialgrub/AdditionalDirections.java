package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;

public class AdditionalDirections extends AppCompatActivity {

    String additionalDirectionString;

    EditText editAdditionalDirection;
    Button continueToConfirmPost;
    Button addAdditionalDirectionButton;


    //From Parcel
    String recipeTitle;
    String recipeDescription;
    Uri imageUri;
    ArrayList<Ingredient> listOfIngredients;
    ArrayList<String> directions;
    ArrayList<Tag> tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_directions);


        unwrapBundle();

        editAdditionalDirection = findViewById(R.id.additionalDirectionsIdEditText);

        continueToConfirmPost = findViewById(R.id.toContinuePostId);
        continueToConfirmPost.setEnabled(false);

        addAdditionalDirectionButton = findViewById(R.id.addAdditionalDirectionButton);
        addAdditionalDirectionButton.setEnabled(false);

        addAdditionalDirectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                additionalDirectionString = editAdditionalDirection.getText().toString();

                directions.add(additionalDirectionString);

                Toast.makeText(AdditionalDirections.this, "Additional directions added to list!", Toast.LENGTH_SHORT).show();

                editAdditionalDirection.getText().clear();

                continueToConfirmPost.setEnabled(true);



            }
        });

        editAdditionalDirection.addTextChangedListener(addAdditionalDirectionTextWatcher);



        continueToConfirmPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirmIngredientsAndDirections = new Intent(AdditionalDirections.this, ConfirmPost.class);
                confirmIngredientsAndDirections.putExtras(buildBundle());
                startActivity(confirmIngredientsAndDirections);
            }
        });






    }




    private Bundle buildBundle()
    {
        Bundle bundle = new Bundle();

        bundle.putParcelable("title", Parcels.wrap(recipeTitle));
        bundle.putParcelable("description", Parcels.wrap(recipeDescription));
        bundle.putParcelable("imageURI", Parcels.wrap(imageUri));
        bundle.putParcelable("ingredients", Parcels.wrap(listOfIngredients));
        bundle.putParcelable("directions", Parcels.wrap(directions));
        bundle.putParcelable("tags", Parcels.wrap(tags));

        return bundle;
    }




    private void unwrapBundle()
    {
        recipeTitle = Parcels.unwrap(getIntent().getParcelableExtra("title"));
        recipeDescription = Parcels.unwrap(getIntent().getParcelableExtra("description"));
        imageUri = Parcels.unwrap(getIntent().getParcelableExtra("imageURI"));
        listOfIngredients = Parcels.unwrap(getIntent().getParcelableExtra("ingredients"));
        directions = Parcels.unwrap(getIntent().getParcelableExtra("directions"));
        tags = Parcels.unwrap(getIntent().getParcelableExtra("tags"));
    }






    private TextWatcher addAdditionalDirectionTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String additionlDirectionInput = editAdditionalDirection.getText().toString();
            addAdditionalDirectionButton.setEnabled(!additionlDirectionInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}