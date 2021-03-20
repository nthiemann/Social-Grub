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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
//import android.widget.Spinner;

public class ExploreActivity extends AppCompatActivity {

    ImageButton settingButton;
    ImageButton createPostButton;
    ImageButton goToProfileButton;
    ImageButton searchButton;


    //String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();


    RecyclerView recyclerViewListOfPosts;
    PostAdapter postAdapter;


    ArrayList<Post> listOfPosts;


    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference retrievesPostFromDatabase = db.getReference("Image Dish");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);



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
                startActivity(goToSearch);
            }
        });

        recyclerViewListOfPosts = findViewById(R.id.listOfPostsRecycler);
        recyclerViewListOfPosts.setLayoutManager(new LinearLayoutManager(this));




        retrievesPostFromDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listOfPosts = new ArrayList<Post>();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    String recipeTitle = dataSnapshot1.child("recipeTitle").getValue().toString();
                    String recipeUrl = dataSnapshot1.child("recipeUrl").getValue().toString();

                    Post post = new Post(recipeUrl,recipeTitle);

                    listOfPosts.add(post);

                }

                postAdapter = new PostAdapter(ExploreActivity.this,listOfPosts);
                recyclerViewListOfPosts.setAdapter(postAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ExploreActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }



}