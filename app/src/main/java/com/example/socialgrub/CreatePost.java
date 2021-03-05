


package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class CreatePost extends AppCompatActivity {

    String recipeTitle;
    String recipeDescription;

    EditText recipeTitleInput;
    EditText recipeDescriptionInput;
    Button postCancelButton;
    Button postRecipeButton;

    Button selectImageFromGalleryButton;
    Button openCameraButton;
    Uri imageUri;
    ImageView pictureToPost;
    String pathToFile;
    Recipe recipe;


    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");

    DatabaseReference getStoresRecipe = db.getReference("Recipe");
    FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();
    StorageReference reference = FirebaseStorage.getInstance().getReference();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        recipeTitleInput = (EditText) findViewById(R.id.recipeTitleInput);
        recipeDescriptionInput = (EditText) findViewById(R.id.descriptionBox);

        postRecipeButton = (Button) findViewById(R.id.postRecipeButton);
        postRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadPost();
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

        Spinner units = findViewById(R.id.measurementSpinner);
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(CreatePost.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.measurememnt));

        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units.setAdapter(unitAdapter);


        pictureToPost = (ImageView) findViewById(R.id.pictureID);

        openCameraButton = (Button) findViewById(R.id.openCameraInCreatePost);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    2);

        }

        openCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadNewImageWithCamera();
            }
        });




        selectImageFromGalleryButton = (Button) findViewById(R.id.selectAnImageInCreatePost);
        selectImageFromGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageFromGallery();
            }
        });





    }

    private void uploadPost() {


        recipeTitle = recipeTitleInput.getText().toString();
        recipeDescription = recipeDescriptionInput.getText().toString();

        // Check for bad input
        if (recipeTitle.isEmpty()) {
            recipeTitleInput.setError("Recipe title required");
            recipeTitleInput.requestFocus();
            return;
        }
        if (recipeDescription.isEmpty()) {
            recipeDescriptionInput.setError("Please provide a brief description");
            recipeDescriptionInput.requestFocus();
            return;
        }


        if(pictureToPost.getDrawable() == null) {

            Toast.makeText(CreatePost.this,
                    "Please select a photo from your gallery or take a new one with your camera to continue.",
                    Toast.LENGTH_LONG).show();
            pictureToPost.requestFocus();

            return;

        }



        if (userID == null) {

            return;
        }

/*
        String getsUserID = userID.getUid();


        recipe = new Recipe(recipeTitle, recipeDescription);
        getStoresRecipe.child(getsUserID).push().setValue(recipe);

        Toast.makeText(CreatePost.this, "Information has been stored in database", Toast.LENGTH_LONG).show();





       recipe = new Recipe(recipeTitle, recipeDescription, imageUri);
        getStoresRecipe.child(getsUserID).push().setValue(recipe);
        Toast.makeText(CreatePost.this, "Information has been stored in database", Toast.LENGTH_LONG).show();
*/

    }





    private void uploadImageFromGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, 2);
    }



    private void uploadNewImageWithCamera() {

        //request for camera runtime permission
        if(ContextCompat.checkSelfPermission( CreatePost.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( CreatePost.this, new String[] {
                    Manifest.permission.CAMERA
            }, 100);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,  100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Camera Upload
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
/*
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            pictureToPost.setImageBitmap(imageBitmap);


            //Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Uri uri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageUri = (Uri) data.getData();


            if(imageUri == null)
            {
                Toast.makeText( CreatePost.this,  "imageUri null after taking pic", Toast.LENGTH_LONG).show();

            }

                pictureToPost.setImageURI(imageUri);
                uploadToFirebase();
            }
        */



        }
        else if(requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri = (Uri) data.getData();
            pictureToPost.setImageURI(data.getData());
            uploadToFirebase();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        //inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void uploadToFirebase() {

        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        //recipe = new Recipe(recipeTitle,recipeDescription,uri.toString());
                        //String getUserID = userID.getUid();

                        String getUserID = getStoresRecipe.push().getKey();

                        recipe = new Recipe(imageUri.toString());

                        getStoresRecipe.child(getUserID).setValue(recipe);
                        Toast.makeText(CreatePost.this, "Uploaded Successfully", Toast.LENGTH_LONG).show();

                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                Toast.makeText( CreatePost.this,  "In progress", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText( CreatePost.this,  "Failed to upload", Toast.LENGTH_LONG).show();
            }
        });

    }



    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton().getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }


}