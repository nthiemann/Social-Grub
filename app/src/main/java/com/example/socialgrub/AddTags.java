package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.MenuItemCompat;

import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AddTags extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference reference = db.getReference().child("1jtXaB0m7-Hd5RcigSl1XRFoJCt5DNDMfgagA8tMOWxo").child("Sheet1");

    Button goToConfirmPage;
    SearchView searchBar;
    ChipGroup tagMainGroup;

    String recipeTitle;
    String recipeDescription;
    Uri imageUri;
    ArrayList<Tag> tags = new ArrayList<>();
    ArrayList<Ingredient> listOfIngredients;
    ArrayList<String> directions;

    HashSet<String> selectedTags = new HashSet<>();

    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;

    HashMap<String,Integer> tagIDMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tags);

        unwrapBundle();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        populateTagsSearchList();
        listView = (ListView) findViewById(R.id.listView);
        goToConfirmPage = (Button) findViewById(R.id.goToConfirmPost);


        //adapter = new ArrayAdapter<>(AddTags.this, android.R.layout.simple_list_item_1, stringArrayList);
        adapter = new ArrayAdapter<>(AddTags.this, android.R.layout.simple_list_item_1, stringArrayList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tagSelected = adapter.getItem(position);
                if (!selectedTags.contains(tagSelected))
                {
                    Toast.makeText(getApplicationContext(), tagSelected + " added", Toast.LENGTH_SHORT).show();
                    Chip chip = new Chip(AddTags.this);
                    chip.setText(tagSelected);
                    chip.setCloseIconVisible(true);

                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectedTags.remove(tagSelected);
                            tagMainGroup.removeView(chip);
                        }
                    });

                    tagMainGroup.addView(chip);
                    selectedTags.add(tagSelected);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Tag already selected", Toast.LENGTH_SHORT).show();
                }
            }
        });


        searchBar = (SearchView) findViewById(R.id.searchView2);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        tagMainGroup = (ChipGroup) findViewById(R.id.chipGroup2);

        listOfIngredients = Parcels.unwrap(getIntent().getParcelableExtra("ingredients"));
        directions = Parcels.unwrap(getIntent().getParcelableExtra("directions"));

        goToConfirmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedTags.size() < 1)
                {
                    Snackbar mySnackbar = Snackbar.make(findViewById(android.R.id.content), "Please provide at least one tag", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                    return;

                }
                if(selectedTags.size() > 10)
                {
                    Snackbar mySnackbar = Snackbar.make(findViewById(android.R.id.content), "Maximum 10 tags permitted", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                    return;
                }

                for (String tagString : selectedTags)
                    tags.add(new Tag(tagString,tagIDMap.get(tagString)));

                Collections.sort(tags);



                Intent createPost = new Intent(AddTags.this,ConfirmPost.class);

                createPost.putExtras(buildBundle());
                startActivity(createPost);
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

    void populateTagsSearchList()
    {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String tagName = dataSnapshot1.child("TagString").getValue().toString();
                    int tagID = ((Long) dataSnapshot1.child("id").getValue()).intValue();

                    tagIDMap.put(tagName,tagID);
                    stringArrayList.add(tagName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AddTags.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}