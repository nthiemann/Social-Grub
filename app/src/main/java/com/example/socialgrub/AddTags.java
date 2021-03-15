package com.example.socialgrub;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Arrays;

public class AddTags extends AppCompatActivity {


    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("1jtXaB0m7-Hd5RcigSl1XRFoJCt5DNDMfgagA8tMOWxo").child("Sheet1");

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
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //hTags(newText);
                return false;
            }
        });



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
    /*private void searchTags(String searchText)
    {
        String searchInputToLower = searchText.toLowerCase();
        String searchInputTOUpper = searchText.toUpperCase();


        if(searchText != null && searchText.length()>0){
            char[] letters=searchText.toCharArray();
            String firstLetter = String.valueOf(letters[0]).toUpperCase();
            String remainingLetters = searchText.substring(1);
            searchText=firstLetter+remainingLetters;
        }
        Query firebaseSearchQuery = reference.child("id").orderByChild("TagString").startAt(searchText)
                .endAt(searchText + "uf8ff");

        firebaseSearchQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //DataCache.clear();
                tagSearchGroup.de();
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        //Now get Scientist Objects and populate our arraylist.
                        Scientist scientist = ds.getValue(Scientist.class);
                        scientist.setKey(ds.getKey());
                        //DataCache.add(scientist);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Utils.show(a, "No item found");
                }
            }}
    }
    private void clearChipGroup(ChipGroup G)
    {
        for (int i = 0; i < G.)
    }*/
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