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







    TextView ingredientListId;
    TextView directionsListId;

    RecyclerView ingredientsView;
    RecyclerView directionsView;
    Button buttonAddMoreIngredients;
    Button buttonAddMoreDirections;
    Button continuePostId;



    String recipeTitle;
    String recipeDescription;
    Uri imageUri;
    ArrayList<Ingredient> listOfIngredients;
    ArrayList<String> directions;
    ArrayList<Tag> tags;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_post);
        unwrapBundle();
        setView();




        //Toast.makeText(ConfirmPost.this, "Tag array size is: " + tags.size(), Toast.LENGTH_SHORT).show();


        //ingredients recycler view and adapter functions
        ingredientsView = findViewById(R.id.recyclerViewIngredientId);
        ingredientsView.setLayoutManager(new LinearLayoutManager(this));
        IngredientsAdapter ingredientsAdapter;
        ingredientsAdapter = new IngredientsAdapter(ConfirmPost.this, listOfIngredients);
        ingredientsView.setAdapter(ingredientsAdapter);


        //directions recycler View and adapter functions
        directionsView = findViewById(R.id.recyclerViewDirectionsId);
        directionsView.setLayoutManager(new LinearLayoutManager(this));
        DirectionsAdapter directionsAdapter;
        directionsAdapter = new DirectionsAdapter(ConfirmPost.this,directions);
        directionsView.setAdapter(directionsAdapter);





        buttonAddMoreIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMoreIngredients = new Intent(ConfirmPost.this, AdditionalIngredients.class);
                addMoreIngredients.putExtras(buildBundle());
                startActivity(addMoreIngredients);
            }
        });


        buttonAddMoreDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMoreDirections = new Intent(ConfirmPost.this, AdditionalDirections.class);
                addMoreDirections.putExtras(buildBundle());
                startActivity(addMoreDirections);
            }
        });






        continuePostId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent finalConfirmPost = new Intent(ConfirmPost.this, FinalConfirmPost.class);
                finalConfirmPost.putExtras(buildBundle());
                startActivity(finalConfirmPost);
            }
        });

    }


    private void setView()
    {


        ingredientListId = findViewById(R.id.ingredientListId);
        directionsListId = findViewById(R.id.directionsListId);

        buttonAddMoreIngredients = findViewById(R.id.buttonAddMoreIngredients);
        buttonAddMoreDirections = findViewById(R.id.buttonAddMoreDirections);
        continuePostId = findViewById(R.id.continuePostId);



    }


    private Bundle buildBundle()
    {
        Bundle bundle = new Bundle();

        bundle.putParcelable("title", Parcels.wrap(recipeTitle));
        bundle.putParcelable("description", Parcels.wrap(recipeDescription));
        bundle.putParcelable("imageURI", Parcels.wrap(imageUri));
        bundle.putParcelable("ingredients", Parcels.wrap(listOfIngredients));
        bundle.putParcelable("directions", Parcels.wrap(directions));
        bundle.putParcelable("tags", Parcels.wrap(tags));

        return bundle;
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