package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ConfirmPost extends AppCompatActivity {

    Recipe recipePost;

    Button cancelPostButton;
    Button changeTitleButton;
    Button changeImageButton;
    Button changeDescriptionButton;
    Button changeIngredientsButton;
    Button changeDirectionsButton;
    Button changeTagsButton;
    Button postRecipeButton;

    ImageView image;
    TextView postTitle;
    TextView description;
    RecyclerView ingredientsView;
    RecyclerView directionsView;
    ChipGroup  tagsGroupView;



    String recipeTitle;
    String recipeDescription;
    String recipeUrl;

    String tag1;
    String tag2;
    String tag3;




    ArrayList<Ingredient> listOfIngredients = new ArrayList<Ingredient>();
    ArrayList<String> directions = new ArrayList<String>();



    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference getStoresRecipe = db.getReference("Image Dish");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_post);

        cancelPostButton = (Button) findViewById(R.id.cancelPostButton);
        changeTitleButton = (Button) findViewById(R.id.changeTitleButton);
        changeImageButton = (Button) findViewById(R.id.changeImageButton);
        changeDescriptionButton = (Button) findViewById(R.id.changeDescriptionButton);
        changeIngredientsButton = (Button) findViewById(R.id.changeIngredientsButton);
        changeDirectionsButton = (Button) findViewById(R.id.changeDirectionsButton);
        changeTagsButton = (Button) findViewById(R.id.changeTagsButton);
        postRecipeButton = (Button) findViewById(R.id.uploadPostButton);

        image = (ImageView) findViewById(R.id.image);

        postTitle = (TextView) findViewById(R.id.postTitle);
        description = (TextView) findViewById(R.id.description);

        // How do you do recyclerviews? or chipgroups?
        //ingredientsView = (RecyclerView) findViewById(R.id.direction)

        recipePost = Parcels.unwrap(getIntent().getParcelableExtra("recipePost"));
        listOfIngredients = Parcels.unwrap(getIntent().getParcelableExtra("ingredient"));
        directions = Parcels.unwrap(getIntent().getParcelableExtra("direction"));

        //listOfIngredients.addAll(recipePost.getIngredients());
       // directions.addAll(recipePost.getDirections());
        tag1 = recipePost.getRecipeTagOne();
        tag2 = recipePost.getRecipeTagTwo();
        tag3 = recipePost.getRecipeTagThree();
        recipeTitle = recipePost.getRecipeTitle();
        recipeDescription = recipePost.getRecipeDescription();
        recipeUrl = recipePost.getRecipeUrl();





        cancelPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfirmPost.this, ExploreActivity.class));

            }
        });

        changeTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changeDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changeIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changeDirectionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        changeTagsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        postRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ConfirmPost.this, ExploreActivity.class));
            }
        });

    }











}