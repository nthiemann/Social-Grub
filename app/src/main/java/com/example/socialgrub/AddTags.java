package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.parceler.Parcels;

import java.util.ArrayList;

public class AddTags extends AppCompatActivity {


    Button goToConfirmPage;
    EditText recipeTagInput;

    Recipe recipePost;
    ArrayList<Ingredient> listOfIngredients = new ArrayList<Ingredient>();
    ArrayList<String> directions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tags);




        recipeTagInput = (EditText) findViewById(R.id.recipeTagInput);

        goToConfirmPage = (Button) findViewById(R.id.goToConfirmPost);


        recipePost = Parcels.unwrap(getIntent().getParcelableExtra("recipePost"));
        listOfIngredients = Parcels.unwrap(getIntent().getParcelableExtra("ingredient"));
        directions = Parcels.unwrap(getIntent().getParcelableExtra("direction"));

        //directions.addAll(recipePost.getDirections());





        goToConfirmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipePost = new Recipe(listOfIngredients,directions,"tag 1", "tag 2", "tag 3");

                Intent createPost = new Intent(AddTags.this,CreatePost.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("recipePost", Parcels.wrap(recipePost));

                bundle.putParcelable("ingredient", Parcels.wrap(listOfIngredients));
                bundle.putParcelable("direction", Parcels.wrap(directions));

                createPost.putExtras(bundle);
                startActivity(createPost);




            }
        });


    }
/*
    private boolean checksForTag(String ingredient1, String direction1) {

        String tagTest = recipeTagInput.getText().toString();


        if (tagTest.isEmpty()) {

            recipeTagInput.setError("Enter Tag");
            recipeTagInput.requestFocus();
            return false;
        } else {

            recipePost = new Recipe(ingredient1,direction1,tagTest);

        }

        return true;


}


 */
}