package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;


public class FinalConfirmPost extends AppCompatActivity {


    ChipGroup tagsGroupView;


    Button postRecipeButton;
    Button cancelPostButton;
    ImageView image;
    TextView postTitle;
    TextView description;
    TextView confirmPostTitle;

    String recipeTitle;
    String recipeDescription;
    Uri imageUri;
    String imageUrl;
    String userID;
    ArrayList<Ingredient> listOfIngredients;
    ArrayList<String> directions;
    ArrayList<Tag> tags;

    Bundle lastBundle;


    String username = "default";

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    private DatabaseReference getStoresRecipe = db.getReference("Image Dish");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_confirm_post);

        unwrapBundle();
        setView();

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        postRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPost();
                startActivity(new Intent(FinalConfirmPost.this, ExploreActivity.class));
                Toast.makeText(FinalConfirmPost.this, "Post is successful!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancelPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalConfirmPost.this, ExploreActivity.class));
                Toast.makeText(FinalConfirmPost.this, "Post was unsuccessful, try again!", Toast.LENGTH_LONG).show();
                finish();
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

                            HashMap<String,Object> postMap;

                            postMap= new HashMap<>();

                            postMap.put("recipeUrl", imageUrl);
                            postMap.put("recipeTitle", recipeTitle);
                            postMap.put("recipeDescription", recipeDescription);
                            postMap.put("Username", username);
                            postMap.put("recipeTags", tags);
                            postMap.put("directions", directions);
                            postMap.put("ingredients", listOfIngredients);
                            postMap.put("postID", postID);

                            getStoresRecipe.child(postID).setValue(postMap);
                            getStoresRecipe.child(postID).child("ratings").getRef().push().setValue(new PostRating(userID, 5.0f));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(FinalConfirmPost.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(FinalConfirmPost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No image was selected!", Toast.LENGTH_SHORT).show();
        }


    }








    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton().getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
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


    private void setView()
    {
        cancelPostButton = (Button) findViewById(R.id.cancelPostButtonId);
        postRecipeButton = (Button) findViewById(R.id.uploadPostId);

        image = (ImageView) findViewById(R.id.imageInConfirmPostId);

        confirmPostTitle = (TextView) findViewById(R.id.confirmPostTitleId);
        postTitle = (TextView) findViewById(R.id.postTitleId);
        description = (TextView) findViewById(R.id.descriptionConfirmPageId);

        postTitle.setText(recipeTitle);
        description.setText(recipeDescription);

        image.setImageURI(imageUri);
    }





}