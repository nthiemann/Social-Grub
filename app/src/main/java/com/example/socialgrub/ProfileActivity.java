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

import com.google.android.gms.auth.api.signin.internal.Storage;
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
    PostAdapter postAdapter;
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference retrievesPostFromDatabaseForUser = db.getReference("Users");
    DatabaseReference dbUsername = db.getReference("Users");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("users/");


    ArrayList<Post> userPostList;

    Button editProfileBtn;
    ImageButton settingsProfileBtn;
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String postID;
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
                userPostList = new ArrayList<>();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    postID = dataSnapshot1.getKey();


                    String recipeTitle = dataSnapshot1.child("recipeTitle").getValue().toString();
                    String recipeURL = dataSnapshot1.child("recipeUrl").getValue().toString();


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
                    //Toast.makeText(ProfileActivity.this, "Rating " + avgRating, Toast.LENGTH_SHORT).show();


                    Post post = new Post(postID, recipeTitle, recipeURL, recipeTags);

                    userPostList.add(post);
                    //Toast.makeText(ProfileActivity.this, "Added post ", Toast.LENGTH_SHORT).show();


                }

                postAdapter = new PostAdapter(ProfileActivity.this,userPostList);
                recyclerView.setAdapter(postAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, "There are no posts under your name", Toast.LENGTH_SHORT).show();
            }

        });
    }
}