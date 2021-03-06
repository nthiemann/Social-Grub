package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.parceler.Parcels;

public class AddTags extends AppCompatActivity {

    Recipe recipePost;
    Button goToConfirmPage;
    EditText recipeTagInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tags);

/*
   Intent intent = getIntent();
        recipePost = (Recipe) intent.getSerializableExtra("recipePost");*/



        recipeTagInput = (EditText) findViewById(R.id.recipeTagInput);

        goToConfirmPage = (Button) findViewById(R.id.goToConfirmPost);


        recipePost = Parcels.unwrap(getIntent().getParcelableExtra("recipePost"));
        String ingredient1 = recipePost.getIngredient1();
        String direction1 = recipePost.getDirection1();


        goToConfirmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checksForTag(ingredient1,direction1)) {


                    //startActivity(new Intent(AddTags.this,CreatePost.class));

                    Intent confirmPageIntent = new Intent(AddTags.this,CreatePost.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("recipePost", Parcels.wrap(recipePost));
                    confirmPageIntent.putExtras(bundle);
                    startActivity(confirmPageIntent);







                }
            }
        });


    }

    private boolean checksForTag(String ingredient1, String direction1) {

        String tagTest = recipeTagInput.getText().toString();


        if (tagTest.isEmpty()) {

            recipeTagInput.setError("Enter Tag");
            recipeTagInput.requestFocus();
            return false;
        } else {

            recipePost = new Recipe(ingredient1,direction1,tagTest);

        }

        return true;


}
}