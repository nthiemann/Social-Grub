package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        recipePost = getIntent().getParcelableExtra("recipePost");

        recipeTagInput = (EditText) findViewById(R.id.recipeTagInput);

        goToConfirmPage = (Button) findViewById(R.id.goToConfirmPost);

        goToConfirmPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checksForTag()) {


                    startActivity(new Intent(AddTags.this,CreatePost.class));

                    Intent confirmPageIntent = new Intent(AddTags.this,CreatePost.class);
                    confirmPageIntent.putExtra("recipePost", recipePost);
                    startActivity(confirmPageIntent);


                }
            }
        });


    }

    private boolean checksForTag() {

        String tagTest = recipeTagInput.getText().toString();


        if (tagTest.isEmpty()) {

            recipeTagInput.setError("Enter Tag");
            recipeTagInput.requestFocus();
            return false;
        } else {

            recipePost = new Recipe();
            recipePost.setTag1(tagTest);
        }

        return true;


}
}