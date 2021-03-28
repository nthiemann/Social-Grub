package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    CircleImageView profileImage;
    Button changeButton,textBoxChangeButton;
    final int IMG_PICK = 1, PERMISSION_CODE = 2;
    StorageReference storageReference;
    FirebaseAuth fAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference ref;
    String userName,newUsername,firstName,newFirstName,lastName,newLastName,newDescription,oldDescription;
    EditText firstNameEditText,lastNameEditText,userNameEditText;
    TextInputEditText changeLastNameEditText,changeFirstNameEditText,newUserNameEditText,textBoxEditText;
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        profileImage = findViewById(R.id.profilepic_imageView);
        changeButton = findViewById(R.id.change_button);
        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        firstNameEditText = findViewById(R.id.fname_editText);
        userNameEditText = findViewById(R.id.username_editText);
        lastNameEditText = findViewById(R.id.lname_editText);
        newUserNameEditText = findViewById(R.id.change_username_editText);
        changeLastNameEditText = findViewById(R.id.change_last_name_editText);
        changeFirstNameEditText = findViewById(R.id.change_first_name_editText);
        textBoxEditText = findViewById(R.id.text_box_editText);
        textBoxChangeButton = findViewById(R.id.change_text_box_button);


        // loads profile image
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(EditProfileActivity.this).load(uri).into(profileImage);
            }
        });

        //loads username,name,lastname
        loadUserName();
        loadFirstName();
        loadLastName();
        loadDescription();



        Button save = findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameChanged()){
                    userNameEditText.setText(newUsername);
                    loadUserName();
                }
                if (firstNameChanged()){
                    firstNameEditText.setText(newFirstName);
                    loadFirstName();
                }
                if (lastnameChanged()){
                    lastNameEditText.setText(newLastName);
                    loadLastName();
                }
            }
        });

        textBoxChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (descriptionChanged()){
                    loadDescription();
                }
            }
        });

        // changes profile image if user clicks change button
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // content uri from image in mediastore is selected
                Intent openGallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery,IMG_PICK);
            }
        });

    }

    private Boolean descriptionChanged() {
        newDescription = textBoxEditText.getText().toString();
        db.getReference().child("Users").child(user.getUid()).child("Description").setValue(newDescription);
        return true;


    }

    private void loadDescription(){
        ref = db.getReference().child("Users").child(user.getUid()).child("Description");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String description = dataSnapshot.getValue().toString();
                textBoxEditText.setText(description);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditProfileActivity.this, "Error displaying description", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserName(){

        ref = db.getReference().child("Users").child(user.getUid()).child("Username");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName = dataSnapshot.getValue(String.class);
                userNameEditText.setText(userName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditProfileActivity.this, "Error: Cannot retrieve user name", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadFirstName(){

        ref = db.getReference().child("Users").child(user.getUid()).child("First name");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fname = dataSnapshot.getValue(String.class);
                firstNameEditText.setText(fname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditProfileActivity.this, "Error: Cannot retrieve first name", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadLastName(){
        ref = db.getReference().child("Users").child(user.getUid()).child("Last name");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lname = dataSnapshot.getValue(String.class);
                lastNameEditText.setText(lname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EditProfileActivity.this, "Error: Cannot retrieve last name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean usernameChanged(){

        userName = userNameEditText.getText().toString();
        newUsername = newUserNameEditText.getText().toString();

        // if the newUsername is empty or the same as old, do not update
        // else update the username onscreen and update the database
        if (!userName.equals(newUsername) && !newUsername.isEmpty()){
            db.getReference().child("Users").child(user.getUid()).child("Username").setValue(newUsername);
            return true;
        }
        return false;
    }

    private Boolean firstNameChanged() {

        firstName = firstNameEditText.getText().toString();
        newFirstName = changeFirstNameEditText.getText().toString();

        if (!firstName.equals(newFirstName) && !newFirstName.isEmpty()){
            db.getReference().child("Users").child(user.getUid()).child("First name").setValue(newFirstName);
            return true;
        }
        return false;
    }

    private Boolean lastnameChanged(){
        lastName = lastNameEditText.getText().toString();
        newLastName = changeLastNameEditText.getText().toString();

        if (!lastName.equals(newLastName) && !newLastName.isEmpty()){
            db.getReference().child("Users").child(user.getUid()).child("Last name").setValue(newLastName);
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_PICK){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();

                //profileImage.setImageURI(imageUri);
                uploadToFirebase(imageUri);
            }
        }
    }

    private void uploadToFirebase(Uri imageUri){
        StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(EditProfileActivity.this).load(uri).into(profileImage);

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfileActivity.this,"failed",Toast.LENGTH_LONG).show();
            }
        });
    }

}