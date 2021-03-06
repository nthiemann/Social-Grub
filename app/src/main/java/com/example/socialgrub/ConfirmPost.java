package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.ChipGroup;

import org.w3c.dom.Text;

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



        Intent intent = getIntent();
        recipePost = (Recipe) intent.getSerializableExtra("recipePost");


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
                uploadPost();
                startActivity(new Intent(ConfirmPost.this, ExploreActivity.class));
            }
        });

    }


    private void uploadPost()
    {
        recipePost.uploadRecipe();
    }


}