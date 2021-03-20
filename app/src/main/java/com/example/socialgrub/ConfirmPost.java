package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ConfirmPost extends AppCompatActivity {

    Button cancelPostButton;
    Button changeTitleButton;
    Button changeImageButton;
    Button changeDescriptionButton;
    Button changeIngredientsButton;
    Button changeDirectionsButton;
    Button changeTagsButton;
    Button postRecipeButton;
    Button backButtonToTags;

    ImageView image;
    TextView postTitle;
    TextView description;
    RecyclerView ingredientsView;
    RecyclerView directionsView;



    ChipGroup  tagsGroupView;


    String recipeTitle;
    String recipeDescription;
    Uri imageUri;
    String imageUrl;
    String userID;
    ArrayList<Ingredient> listOfIngredients;
    ArrayList<String> directions;
    ArrayList<Tag> tags;





    String username = "default";

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    private DatabaseReference getStoresRecipe = db.getReference("Image Dish");


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_post);
        unwrapBundle();
        setView();

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        Toast.makeText(ConfirmPost.this, "Tag array size is: " + tags.size(), Toast.LENGTH_SHORT).show();


        ingredientsView = findViewById(R.id.recyclerViewIngredientId);
        ingredientsView.setLayoutManager(new LinearLayoutManager(this));
        IngredientsAdapter ingredientsAdapter;
        ingredientsAdapter = new IngredientsAdapter(ConfirmPost.this, listOfIngredients);
        ingredientsView.setAdapter(ingredientsAdapter);




        directionsView = findViewById(R.id.recyclerViewDirectionsId);
        directionsView.setLayoutManager(new LinearLayoutManager(this));
        DirectionsAdapter directionsAdapter;
        directionsAdapter = new DirectionsAdapter(ConfirmPost.this,directions);
        directionsView.setAdapter(directionsAdapter);



        postRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPost();
                startActivity(new Intent(ConfirmPost.this, ExploreActivity.class));
            }
        });

    }

    private void uploadPost() {
        if (imageUri != null) {
            final StorageReference filePath = FirebaseStorage.getInstance().getReference("Posts").child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            StorageTask uploadtask = filePath.putFile(imageUri);
            uploadtask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return filePath.getDownloadUrl();
                }

            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    Uri downloadUri = task.getResult();
                    imageUrl = downloadUri.toString();

                    String postID = getStoresRecipe.push().getKey();
                    Recipe recipePost = new Recipe(listOfIngredients,directions,tags,recipeTitle,recipeDescription,imageUrl);
                    DatabaseReference userRef = db.getReference().child("Users").child(userID);
                    userRef.child("Recipes").child(postID).setValue(recipePost);


                    DatabaseReference getusername = db.getReference("Users").child(userID).child("Username");

                    getusername.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            username = snapshot.getValue().toString();

                            HashMap<String,Object>postMap;

                            postMap= new HashMap<>();

                            postMap.put("recipeUrl", imageUrl);
                            postMap.put("recipeTitle", recipeTitle);
                            postMap.put("recipeDescription", recipeDescription);
                            postMap.put("Username", username);

                            getStoresRecipe.child(postID).setValue(postMap);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(ConfirmPost.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ConfirmPost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No image was selected!", Toast.LENGTH_SHORT).show();
        }


    }


    private void setView()
    {
        cancelPostButton = (Button) findViewById(R.id.cancelPostButtonId);
        //changeTitleButton = (Button) findViewById(R.id.changeTitleButton);
        //changeImageButton = (Button) findViewById(R.id.changeImageButton);
        //changeDescriptionButton = (Button) findViewById(R.id.changeDescriptionButton);
        //changeIngredientsButton = (Button) findViewById(R.id.changeIngredientsButton);
        //changeDirectionsButton = (Button) findViewById(R.id.changeDirectionsButton);
        //changeTagsButton = (Button) findViewById(R.id.changeTagsButton);
        postRecipeButton = (Button) findViewById(R.id.uploadPostId);
        backButtonToTags = (Button) findViewById(R.id.backButtonId);

        image = (ImageView) findViewById(R.id.imageInConfirmPostId);

        postTitle = (TextView) findViewById(R.id.postTitleId);
        description = (TextView) findViewById(R.id.descriptionConfirmPageId);

        postTitle.setText(recipeTitle);
        description.setText(recipeDescription);

        image.setImageURI(imageUri);
    }

    private void unwrapBundle()
    {
        recipeTitle = Parcels.unwrap(getIntent().getParcelableExtra("title"));
        recipeDescription = Parcels.unwrap(getIntent().getParcelableExtra("description"));
        imageUri = Parcels.unwrap(getIntent().getParcelableExtra("imageURI"));
        listOfIngredients = Parcels.unwrap(getIntent().getParcelableExtra("ingredients"));
        directions = Parcels.unwrap(getIntent().getParcelableExtra("directions"));
        tags = Parcels.unwrap(getIntent().getParcelableExtra("tags"));
    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton().getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }



}