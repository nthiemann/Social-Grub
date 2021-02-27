package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ProfileActivity extends AppCompatActivity {
    Button editProfileBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editProfileBtn = (Button) findViewById(R.id.edit_profile_button);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToeditPage = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(goToeditPage);
            }
        });







    }
}