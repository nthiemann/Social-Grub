package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

    String recipeTitle;
    String recipeDescription;
    Uri imageUri;
    ArrayList<Tag> tags;
    ArrayList<Ingredient> listOfIngredients;
    ArrayList<String> directions;

    // temporary string to capture one single tag (until tag page is done)
    String tagText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tags);

        unwrapBundle();
        tags = new ArrayList<>();

        recipeTagInput = (EditText) findViewById(R.id.recipeTagInput);
        goToConfirmPage = (Button) findViewById(R.id.goToConfirmPost);


        //recipePost = Parcels.unwrap(getIntent().getParcelableExtra("recipePost"));
        listOfIngredients = Parcels.unwrap(getIntent().getParcelableExtra("ingredients"));
        directions = Parcels.unwrap(getIntent().getParcelableExtra("directions"));
        //directions.addAll(recipePost.getDirections());



        goToConfirmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (getTagText())
                {
                    tags.add(new Tag(tagText));

                    Intent createPost = new Intent(AddTags.this,ConfirmPost.class);

                    createPost.putExtras(buildBundle());
                    startActivity(createPost);
                }

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
    }

    private boolean getTagText()
    {
        tagText = recipeTagInput.getText().toString();

        // Check for bad input
        if (tagText.isEmpty()) {
            recipeTagInput.setError("At least one tag required");
            recipeTagInput.requestFocus();
            return false;
        }
        return true;
    }
}