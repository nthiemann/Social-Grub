package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.parceler.Parcels;

import java.util.ArrayList;


public class AddDirections extends AppCompatActivity {
    Recipe recipePost;
    Button nextButtonToGoToTags;

    EditText descriptionBox1;
    EditText descriptionBox2;
    EditText descriptionBox3;
    EditText descriptionBox4;
    EditText descriptionBox5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_directions);
/*
        Intent intent = getIntent();
         recipePost = (Recipe) intent.getSerializableExtra("recipePost");
         */

        //recipePost = getIntent().getParcelableExtra("recipePost");

         nextButtonToGoToTags = (Button) findViewById(R.id.buttonNextToAddTags);

         descriptionBox1 = (EditText) findViewById(R.id.descriptionBox1ID);
        descriptionBox2 = (EditText) findViewById(R.id.descriptionBox2ID);
        descriptionBox3 = (EditText) findViewById(R.id.descriptionBox3ID);
        descriptionBox4 = (EditText) findViewById(R.id.descriptionBox4ID);
        descriptionBox5 = (EditText) findViewById(R.id.descriptionBox5ID);


        recipePost = Parcels.unwrap(getIntent().getParcelableExtra("recipePost"));
        String ingredient1 = recipePost.getIngredient1();

        nextButtonToGoToTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checksForDirectionsFilledIn(ingredient1)) {

                    //startActivity(new Intent(AddDirections.this,AddTags.class));

                    Intent tagsPageIntent = new Intent(AddDirections.this,AddTags.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("recipePost", Parcels.wrap(recipePost));
                    tagsPageIntent.putExtras(bundle);
                    startActivity(tagsPageIntent);

                }
            }
        });
    }

    private boolean checksForDirectionsFilledIn(String ingredient1) {

        String descriptionBox1String = descriptionBox1.getText().toString();
        String descriptionBox2String = descriptionBox2.getText().toString();
        String descriptionBox3String = descriptionBox3.getText().toString();
        String descriptionBox4String = descriptionBox4.getText().toString();
        String descriptionBox5String = descriptionBox5.getText().toString();


        if(descriptionBox1String.isEmpty()) {

            descriptionBox1.setError("Text Box 1 is empty");
            descriptionBox1.requestFocus();
            return false;

        }


        else {

           recipePost = new Recipe(ingredient1,descriptionBox1String);

        }


/*
        if(descriptionBox2String.isEmpty()) {

            descriptionBox2.setError("Text Box 2 is empty");
            descriptionBox2.requestFocus();
            return false;

        }


        else {

            recipePost = new Recipe();
            recipePost.setRecipeDirection2(descriptionBox2String);

        }



        if(descriptionBox3String.isEmpty()) {

            descriptionBox3.setError("Text Box 3 is empty");
            descriptionBox3.requestFocus();
            return false;

        }


        else {

            recipePost = new Recipe();
            recipePost.setRecipeDirection3(descriptionBox3String);

        }


        if(descriptionBox4String.isEmpty()) {

            descriptionBox4.setError("Text Box 4 is empty");
            descriptionBox4.requestFocus();
            return false;

        }


        else {

            recipePost = new Recipe();
            recipePost.setRecipeDirection4(descriptionBox4String);

        }




        if(descriptionBox5String.isEmpty()) {

            descriptionBox5.setError("Text Box 5 is empty");
            descriptionBox5.requestFocus();
            return false;

        }


        else {

            recipePost = new Recipe();
            recipePost.setRecipeDirection5(descriptionBox5String);


        }
        */



        return true;




        }




    }


