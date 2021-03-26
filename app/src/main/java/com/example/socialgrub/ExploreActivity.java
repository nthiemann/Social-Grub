package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashSet;
//import android.widget.Spinner;

public class ExploreActivity extends AppCompatActivity {

    ImageButton settingButton;
    ImageButton createPostButton;
    ImageButton goToProfileButton;
    ImageButton searchButton;
    ProgressBar spinner;




    RecyclerView recyclerViewListOfPosts;
    PostAdapter postAdapter;


    ArrayList<Post> listOfPosts = new ArrayList<>();
    ArrayList<Post> displayedPosts = new ArrayList<>();



    final int SEARCH_REQUEST_CODE = 42;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference retrievesPostFromDatabase = db.getReference("Image Dish");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        spinner = (ProgressBar)findViewById(R.id.progressBar2);

        settingButton = (ImageButton) findViewById(R.id.settings_button);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExploreActivity.this, SettingsActivity.class));
            }
        });

        createPostButton = (ImageButton) findViewById(R.id.createRecipePage);
        createPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCreatePostActivityPage = new Intent(ExploreActivity.this, CreatePost.class);
                startActivity(goToCreatePostActivityPage);
            }
        });

        goToProfileButton = (ImageButton) findViewById(R.id.profileButton);
        goToProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToProfilePage = new Intent(ExploreActivity.this, ProfileActivity.class);
                startActivity(goToProfilePage);
            }
        });

        searchButton = (ImageButton) findViewById(R.id.search_icon_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearch = new Intent(ExploreActivity.this, Search.class);
                //startActivity(goToSearch);
                startActivityForResult(goToSearch, SEARCH_REQUEST_CODE);

            }
        });

        recyclerViewListOfPosts = findViewById(R.id.listOfPostsRecycler);
        recyclerViewListOfPosts.setLayoutManager(new LinearLayoutManager(this));


        retrievesPostFromDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listOfPosts.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                    String recipeID = dataSnapshot1.getKey();
                    String recipeTitle = dataSnapshot1.child("recipeTitle").getValue().toString();
                    String recipeUrl = dataSnapshot1.child("recipeUrl").getValue().toString();
                    String username = dataSnapshot1.child("Username").getValue().toString();
                    ArrayList<String> recipeTags = new ArrayList<>();
                    for (DataSnapshot thisId : dataSnapshot1.child("recipeTags").getChildren())
                    {
                        recipeTags.add((String) thisId.child("tagName").getValue());
                    }

                    int ratingCount = 0;
                    double avgRating = 0;
                    if (dataSnapshot1.hasChild("ratings"))
                    {

                        for (DataSnapshot s : dataSnapshot1.child("ratings").getChildren()) {
                            avgRating += Double.parseDouble(s.child("rating").getValue().toString());
                            ratingCount++;
                        }
                    }
                    avgRating /= (double)ratingCount;
                    Post post = new Post(recipeID, recipeTitle, recipeUrl, recipeTags, username, (float)avgRating);


                    listOfPosts.add(post);
                }
                displayedPosts = listOfPosts;

                postAdapter = new PostAdapter(ExploreActivity.this,displayedPosts);
                recyclerViewListOfPosts.setAdapter(postAdapter);
                spinner.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ExploreActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // This receives the filtered list from search activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == SEARCH_REQUEST_CODE &&
                resultCode == RESULT_OK) {

            ArrayList<Post> filteredPosts = intent.getParcelableArrayListExtra("filteredPosts");

            //Toast.makeText(ExploreActivity.this, "filtered post size " + filteredPosts.size(), Toast.LENGTH_SHORT).show();
            setDisplayedPosts(filteredPosts);
        }
    }

    private void setDisplayedPosts(ArrayList<Post> postList)
    {

        if (postList.size() > 0)
        {
            displayedPosts = postList;

            postAdapter = new PostAdapter(ExploreActivity.this,displayedPosts);
            recyclerViewListOfPosts.setAdapter(postAdapter);
        }
        else
        {
            Snackbar mySnackbar = Snackbar.make(findViewById(android.R.id.content), "Search returned no results", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        }

    }

}