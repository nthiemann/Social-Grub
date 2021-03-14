package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.android.material.chip.ChipGroup;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Arrays;

public class AddTags extends AppCompatActivity {


    Button goToConfirmPage;
    EditText recipeTagInput;
    SearchView searchBar;
    ChipGroup tagSearchGroup;
    ChipGroup tagMainGroup;

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
        searchBar = (SearchView) findViewById(R.id.searchView2);
        tagSearchGroup = (ChipGroup) findViewById(R.id.chipGroup);
        tagMainGroup = (ChipGroup) findViewById(R.id.chipGroup2);


        listOfIngredients = Parcels.unwrap(getIntent().getParcelableExtra("ingredients"));
        directions = Parcels.unwrap(getIntent().getParcelableExtra("directions"));

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

    // populates the main tags chip group with pre selected tags
    private void populateDefaultTags()
    {
        // create list of tag ID's
        ArrayList<Integer> defaultMainGroup = new ArrayList<>(Arrays.asList(7,11,17,24,32,81,84,98,143,172));

        /*for (Integer i : defaultMainGroup)
        {
            tagMainGroup.addView();
        }*/
    }

    // Allows user to search tags in database, which then updates tagSearchGroup to display most like the search
    private void searchTags()
    {

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