
package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;


public class CreatePost extends AppCompatActivity {


    Recipe recipePost;
    String recipeTitle;
    String recipeDescription;
    String imageUrl;

    EditText recipeTitleInput;
    EditText recipeDescriptionInput;

    Button postCancelButton;
    Button continueRecipeButton; //will upload image to Firebase Storage
    Button selectAnImageButton;
    Button openCameraButton;

    ImageView pictureToPost;

    Uri imageUri;

    Bitmap image;
    StorageReference reference = FirebaseStorage.getInstance().getReference();


   FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
   DatabaseReference getStoresRecipe = db.getReference("Image Dish");


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        recipeTitleInput = (EditText) findViewById(R.id.recipeTitleInput);
        recipeDescriptionInput = (EditText) findViewById(R.id.descriptionBox);
        continueRecipeButton = (Button) findViewById(R.id.continueRecipeButton);
        pictureToPost = (ImageView) findViewById(R.id.pictureID);
        selectAnImageButton = (Button) findViewById(R.id.selectAnImage);
        openCameraButton = (Button) findViewById(R.id.openCameraButton);


        //function will upload image to Firebase, there wont be any toast for now,
        //give it like 30 seconds and you will see image in firebase
        selectAnImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoad();
            }
        });

        //function does not work DO NOT PRESS OPEN CAMERA BUTTON
        openCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               uploadNewImageWithCamera();



            }
        });


        continueRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (enterPostTitleNameDescriptionAndPhoto())
                {
                    Intent ingredientsIntent = new Intent(CreatePost.this,AddIngredients.class);
                    ingredientsIntent.putExtra("recipePost",recipePost);
                    startActivity(ingredientsIntent);
                }
            }
        });

        postCancelButton = (Button) findViewById(R.id.postCancelButton);
        postCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackToExploreAndCancel = new Intent(CreatePost.this, ExploreActivity.class);
                startActivity(goBackToExploreAndCancel);
            }
        });

            //CropImage.activity().start(CreatePost.this);
           CropImage.activity().start(CreatePost.this);
    }


    private boolean enterPostTitleNameDescriptionAndPhoto() {
        recipeTitle = recipeTitleInput.getText().toString();
        recipeDescription = recipeDescriptionInput.getText().toString();

        // Check for bad input
        if (recipeTitle.isEmpty()) {
            recipeTitleInput.setError("Recipe title required");
            recipeTitleInput.requestFocus();
            return false;
        }
        if (recipeDescription.isEmpty()) {
            recipeDescriptionInput.setError("Please provide a brief description");
            recipeDescriptionInput.requestFocus();
            return false;
        }


        if(pictureToPost.getDrawable() == null) {

            Toast.makeText(CreatePost.this,
                    "Please select a photo from your gallery or take a new one with your camera to continue.",
                    Toast.LENGTH_LONG).show();
            pictureToPost.requestFocus();

            return false;

        }

        return true;

    }


    private void uploadImageFromGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, 2);
    }


    private void uploadNewImageWithCamera() {

        //request for camera runtime permission
        if (ContextCompat.checkSelfPermission(CreatePost.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CreatePost.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(resultCode == RESULT_OK && data != null && requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
                imageUri = result.getUri();
                pictureToPost.setImageURI(imageUri);


        }
        else {
            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CreatePost.this , ExploreActivity.class));
            finish();
        }


    }



    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton().getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }




    private void upLoad() {
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


                    //the map does not seem to get recipeDescription nor recipeTitle

                    recipePost = new Recipe();
                    //recipePost.setPostID(postID);
                    recipePost.setImageURL(imageUrl);
                    recipePost.setRecipeTitle(recipeTitle);
                    recipePost.setRecipeDescription(recipeDescription);


                    /*recipeMap = new HashMap<>();
                    recipeMap.put("postId", postId);
                    recipeMap.put("imageUrl", imageUrl);
                    recipeMap.put("description", recipeDescription);
                    recipeMap.put("Title", recipeTitle);*/


                    //getStoresRecipe.child(postId).setValue(recipeMap);


                }





            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CreatePost.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No image was selected!", Toast.LENGTH_SHORT).show();
        }


    }




    }















