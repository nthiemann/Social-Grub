package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class DisplayIngredientAndDirections extends AppCompatActivity  {

    Button buttonToGoToExplore;


    ArrayList<Ingredient> listOfIngredients = new ArrayList<>();
    ArrayList<String> directions = new ArrayList<>();
    ArrayList<String> tags = new ArrayList<>();

    ImageView picture;
    TextView postTitleView;
    RecyclerView recyclerViewIngredient;
    RecyclerView recyclerViewDirections;
    RatingBar ratingBarTop;
    TextView ratingText;
    RatingBar ratingBarBottom;
    ChipGroup tagChipGroup;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference retrievesPostFromDatabase = db.getReference("Image Dish");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_ingredient_and_directions);

        buttonToGoToExplore = findViewById(R.id.buttonGoBackToPost);
        picture = findViewById(R.id.imageView);
        postTitleView = (TextView) findViewById(R.id.titleView);
        ratingBarTop = findViewById(R.id.ratingBar2);
        ratingText = findViewById(R.id.ratingText);
        ratingBarBottom = findViewById(R.id.ratingBar);
        tagChipGroup = findViewById(R.id.chipGroup);

        recyclerViewDirections = findViewById(R.id.recyclerViewDirections);
        recyclerViewIngredient = findViewById(R.id.recyclerViewIngredient);


        String postID = Parcels.unwrap(getIntent().getParcelableExtra("postID"));

        getPostInfo(postID);
        recyclerViewIngredient.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewDirections.setLayoutManager(new LinearLayoutManager(this));

        buttonToGoToExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getPostInfo(String postID) {

        retrievesPostFromDatabase.child(postID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String recipeName = dataSnapshot.child("recipeTitle").getValue().toString();
                postTitleView.setText(recipeName);

                String imageURL = dataSnapshot.child("recipeUrl").getValue().toString();
                Picasso.get().load(imageURL).into(picture);

                DataSnapshot idListSnapshot = dataSnapshot.child("recipeTags");
                for (DataSnapshot thisID : idListSnapshot.getChildren())
                {
                    String tagName = thisID.child("tagName").getValue().toString();
                    Chip chip = new Chip(DisplayIngredientAndDirections.this);
                    chip.setText(tagName);
                    tagChipGroup.addView(chip);
                }


                DataSnapshot directionListSnapshot = dataSnapshot.child("directions");


                int numberOfDirections = (int) directionListSnapshot.getChildrenCount();
                for(int i = 0; i < numberOfDirections; i++) {

                    String thisDirection = directionListSnapshot.child(String.valueOf(i)).getValue().toString();;
                    directions.add(thisDirection);

                }

                DataSnapshot ingredientListSnapshot = dataSnapshot.child("ingredients");
                for (DataSnapshot thisIngredient : ingredientListSnapshot.getChildren())
                {
                        String ingredientName = thisIngredient.child("nameOfIngredient").getValue().toString();
                    // This needs to be converted to double
                    double measurementValue = Double.parseDouble(thisIngredient.child("measurementValue").getValue().toString());
                    String measurementUnit = thisIngredient.child("measurementUnit").getValue().toString();

                    listOfIngredients.add(new Ingredient(ingredientName, measurementValue,  measurementUnit));
                }
                IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(DisplayIngredientAndDirections.this, listOfIngredients);
                recyclerViewIngredient.setAdapter(ingredientsAdapter);

                DirectionsAdapter directionsAdapter = new DirectionsAdapter(DisplayIngredientAndDirections.this, directions);
                recyclerViewDirections.setAdapter(directionsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DisplayIngredientAndDirections.this, "Data not found", Toast.LENGTH_SHORT).show();

            }

        });


    }



}