package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DisplayIngredientAndDirections extends AppCompatActivity  {

    Button buttonToGoToExplore;
    Button leaveComment;
    Button goToCommentList;


    ArrayList<Ingredient> listOfIngredients = new ArrayList<>();
    ArrayList<String> directions = new ArrayList<>();
    ArrayList<String> tags = new ArrayList<>();



    ImageView picture;
    TextView postTitleView;
    TextView descriptionView;
    RecyclerView recyclerViewIngredient;
    RecyclerView recyclerViewDirections;
    RatingBar ratingBarTop;
    TextView ratingText;
    TextView userView;
    RatingBar ratingBarBottom;
    ChipGroup tagChipGroup;
    EditText textBoxForComments;

    String postID;
    String aComment;
    //String username;
    String uniqueCommentId;

    FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
    DatabaseReference retrievesPostFromDatabase = db.getReference("Image Dish");
    DatabaseReference userRef = db.getReference("Users");
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    boolean userHasRated;

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
        userView = findViewById(R.id.userView);
        descriptionView = findViewById(R.id.descriptionText);
        recyclerViewDirections = findViewById(R.id.recyclerViewDirections);
        recyclerViewIngredient = findViewById(R.id.recyclerViewIngredient);


        postID = Parcels.unwrap(getIntent().getParcelableExtra("postID"));



        leaveComment = findViewById(R.id.leaveComment);
        goToCommentList = findViewById(R.id.viewComment);
        textBoxForComments = findViewById(R.id.textBoxForComments);




        leaveComment.setEnabled(false);
        leaveComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aComment = textBoxForComments.getText().toString();

                //places the comment inside the database under that post
                //will generate a random key for that comment
                uniqueCommentId = retrievesPostFromDatabase.push().getKey();
                retrievesPostFromDatabase.child(postID).child("Comments").child(uniqueCommentId).child("Comment String").setValue(aComment);
                Toast.makeText(DisplayIngredientAndDirections.this, "Your comment has been added!", Toast.LENGTH_SHORT).show();
                textBoxForComments.getText().clear();



                userRef.child(userID).child("Username").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                       String username = dataSnapshot.getValue().toString();
                        retrievesPostFromDatabase.child(postID).child("Comments").child(uniqueCommentId).child("Username").setValue(username);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {



                    }






                });



            }
        });





        textBoxForComments.addTextChangedListener(addCommentTextWatcher);



        ratingBarTop.setMax(5);
        ratingBarTop.setStepSize(0.1f);
        ratingBarTop.setFocusable(false);
        ratingBarTop.setIsIndicator(true);


        getPostInfo(postID);
        recyclerViewIngredient.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDirections.setLayoutManager(new LinearLayoutManager(this));


        buttonToGoToExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ratingBarBottom.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                updateRating((float)rating);
            }
        });







        goToCommentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToViewComments = new Intent(DisplayIngredientAndDirections.this, DisplayComments.class);

                Bundle bundle = new Bundle();
                bundle.putParcelable("postID", Parcels.wrap(postID));


                goToViewComments.putExtras(bundle);
                startActivity(goToViewComments);

            }
        });








    }
    private void updateRating(float rating){

        retrievesPostFromDatabase.child(postID).child("ratings").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean hasRated = false;
                for (DataSnapshot s : dataSnapshot.getChildren())
                {
                    if (s.child("userID").getValue().toString().equals(userID))
                    {
                        s.child("rating").getRef().setValue(rating);
                        hasRated = true;
                    }
                }
                if (!hasRated)
                    retrievesPostFromDatabase.child(postID).child("ratings").getRef().push().setValue(new PostRating(userID, (float)rating));

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DisplayIngredientAndDirections.this, "Cannot load data", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void getPostInfo(String postID) {

        retrievesPostFromDatabase.child(postID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int ratingCount = 0;
                double avgRating = 0;
                if (dataSnapshot.hasChild("ratings"))
                {

                    for (DataSnapshot s : dataSnapshot.child("ratings").getChildren()) {
                        avgRating += Double.parseDouble(s.child("rating").getValue().toString());
                        ratingCount++;
                    }
                }
                avgRating /= (double)ratingCount;

                ratingBarTop.setRating((float)avgRating);
                DecimalFormat df = new DecimalFormat("###.###");
                ratingText.setText("Rating: " + df.format(avgRating) + " / 5");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


                retrievesPostFromDatabase.child(postID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String recipeName = dataSnapshot.child("recipeTitle").getValue().toString();
                postTitleView.setText(recipeName);

                String creatorName = dataSnapshot.child("Username").getValue().toString();
                userView.setText("By " +creatorName);


                String imageURL = dataSnapshot.child("recipeUrl").getValue().toString();
                Picasso.get().load(imageURL).into(picture);

                String description = dataSnapshot.child("recipeDescription").getValue().toString();
                descriptionView.setText(description);

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

    private TextWatcher addCommentTextWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String commentInput = textBoxForComments.getText().toString();
            leaveComment.setEnabled(!commentInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };





}