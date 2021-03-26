package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    RecyclerView recyclerView;
    RecipeAdapter recipeAdapter;
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference retrievesPostFromDatabaseForUser = db.getReference("Users");
    DatabaseReference dbUsername = db.getReference("Users");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("users/");

    ArrayList<Recipe> userRecipeList;

    Button editProfileBtn;
    ImageButton settingsProfileBtn;
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    TextView pUsername;
    TextView pName;
    TextView pLastName;
    TextView pDescription;
    ImageView pPicture;
    StorageReference photoReference= storageReference.child(userID).child("profile.jpg");

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

        pUsername = (TextView) findViewById(R.id.profileUsername);
        pName = (TextView) findViewById(R.id.profileName);
        pLastName = (TextView) findViewById(R.id.profileLastName);
        pDescription = (TextView) findViewById(R.id.profileDescription);
        pPicture = (ImageView) findViewById(R.id.profilePicture);

        final long ONE_MEGABYTE = 1024 * 1024;
        photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                pPicture.setImageBitmap(bmp);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(), "The user does not have a profile picture", Toast.LENGTH_LONG).show();
            }
        });

        dbUsername.child(userID).child("Username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String n = dataSnapshot.getValue().toString();
                pUsername.setText(n);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dbUsername.child(userID).child("First name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String n = dataSnapshot.getValue().toString();
                pName.setText(n);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dbUsername.child(userID).child("Last name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String n = dataSnapshot.getValue().toString();
                pLastName.setText(n);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dbUsername.child(userID).child("Description").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String n = dataSnapshot.getValue().toString();
                pDescription.setText(n);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                    String tag = dataSnapshot1.child("recipeTags").child("0").child("tagName").getValue().toString();

                    if(dataSnapshot1.getKey().equals("directions")) {
                        for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                            directions.add(dataSnapshot2.getValue().toString());
                        }
                    }

                    Recipe recipe = new Recipe(recipeURL, recipeTitle, description, tag);
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