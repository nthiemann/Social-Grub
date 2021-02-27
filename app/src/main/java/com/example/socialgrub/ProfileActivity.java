package com.example.socialgrub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class ProfileActivity extends AppCompatActivity {
    Button editProfileBtn;
    ImageButton settingsProfileBtn;

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

        settingsProfileBtn = (ImageButton) findViewById(R.id.settings_profile_button);

        settingsProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( ProfileActivity.this, SettingsActivity.class));
            }
        });







    }
}