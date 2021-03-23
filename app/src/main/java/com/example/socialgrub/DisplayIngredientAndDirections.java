package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DisplayIngredientAndDirections extends AppCompatActivity  {

    Button buttonToGoToExplore;

    ArrayList<Ingredient> listOfIngredients;
    ArrayList<String> directions;
    ArrayList<Tag> tags;

    RecyclerView recyclerViewIngredient;
    RecyclerView recyclerViewDirections;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference retrievesPostFromDatabase = db.getReference("Image Dish");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ingredient_and_directions);

        buttonToGoToExplore = findViewById(R.id.buttonGoBackToPost);


        String postID = Parcels.unwrap(getIntent().getParcelableExtra("postID"));

        Toast.makeText(DisplayIngredientAndDirections.this, "Post ID: " + postID, Toast.LENGTH_SHORT).show();
    /*
        getsDirectionsFromDatabase(postID);
        recyclerViewDirections = findViewById(R.id.recyclerViewDirections);
        recyclerViewDirections.setLayoutManager(new LinearLayoutManager(this));
        DirectionsAdapter directionsAdapter = new DirectionsAdapter(DisplayIngredientAndDirections.this, directions);
        recyclerViewDirections.setAdapter(directionsAdapter);


/*

        getsIngredientsDatabase(postID);
        recyclerViewIngredient = findViewById(R.id.recyclerViewIngredient);
        recyclerViewIngredient.setLayoutManager(new LinearLayoutManager(this));
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(DisplayIngredientAndDirections.this, listOfIngredients);
        recyclerViewIngredient.setAdapter(ingredientsAdapter);
*/


    }


/*

    private void getsDirectionsFromDatabase(String postID) {

        retrievesPostFromDatabase.child(postID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                int numberOfDirections = (int) dataSnapshot.getChildrenCount();


                for(int indexOfDirections = 0; indexOfDirections < numberOfDirections; indexOfDirections++) {

                        //removed code I cant remember what I had here.



                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DisplayIngredientAndDirections.this, "No directions found", Toast.LENGTH_SHORT).show();

            }




        });


    }
*/

/*
    private void getsIngredientsDatabase(String postID) {

        retrievesPostFromDatabase.child(postID).child("ingredients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int numberOfIngredients = (int) dataSnapshot.getChildrenCount();

                for(int indexOfListIngredients = 0 ; indexOfListIngredients < numberOfIngredients; indexOfListIngredients++) {

                        String tempString = indexOfListIngredients + "";

                        String measurementValueTemp = dataSnapshot.child(tempString).child("measurementValue").getValue().toString();
                        String measurementUnit = dataSnapshot.child(tempString).child("measurementUnit").getValue().toString();
                        String nameOfIngredient = dataSnapshot.child(tempString).child("nameOfIngredient").getValue().toString();

                        double measurementValue = Double.parseDouble(measurementValueTemp);




                        Ingredient ingredient = new Ingredient(nameOfIngredient,measurementValue,measurementUnit);
                        listOfIngredients.add(ingredient);



                }


                    //adapter stuff



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(DisplayIngredientAndDirections.this, "No Ingredients found", Toast.LENGTH_SHORT).show();

            }
        });



    }
    */


    /*
    private void getsTagsDatabase() {




    }
    */


}