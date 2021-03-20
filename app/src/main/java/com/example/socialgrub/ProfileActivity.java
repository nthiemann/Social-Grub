package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    RecyclerView recyclerView;
    RecipeAdapter recipeAdapter;
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference retrievesPostFromDatabaseForUser = db.getReference("Users");

    ArrayList<Recipe> userRecipeList;

    Button editProfileBtn;
    ImageButton settingsProfileBtn;
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerView = findViewById(R.id.profilePostView);

        editProfileBtn = (Button) findViewById(R.id.edit_profile_button);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToeditPage = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(goToeditPage);
            }
        });

        settingsProfileBtn = (ImageButton) findViewById(R.id.settings_profile_button);

        settingsProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( ProfileActivity.this, SettingsActivity.class));
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        retrievesPostFromDatabaseForUser.child(userID).child("Recipes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userRecipeList = new ArrayList<>();
                ArrayList<String> directions = null;
                // ArrayList<Ingredient> ingredients;
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {



                    String recipeTitle = dataSnapshot1.child("recipeTitle").getValue().toString();
                    String description = dataSnapshot1.child("recipeDescription").getValue().toString();
                    String recipeURL = dataSnapshot1.child("recipeUrl").getValue().toString();

                    if(dataSnapshot1.getKey().equals("directions")) {
                        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {


                            directions.add(dataSnapshot2.getValue().toString());


                        }
                    }





                    Recipe recipe = new Recipe(recipeURL, recipeTitle,description);
                    userRecipeList.add(recipe);

                }

                recipeAdapter = new RecipeAdapter(ProfileActivity.this,userRecipeList);
                recyclerView.setAdapter(recipeAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, "There are no posts under your name", Toast.LENGTH_SHORT).show();
            }

        });




    }
}