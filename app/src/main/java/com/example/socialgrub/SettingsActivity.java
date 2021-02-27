package com.example.socialgrub;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    Button resetPassword;
    Button logOutOfAccount;
    ImageButton outOfSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        resetPassword = findViewById(R.id.resetPasswordButton);
        logOutOfAccount = findViewById(R.id.logOutofAccountButton);
        outOfSettings = findViewById(R.id.getOutOfSettings);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingsActivity.this, ForgotPasswordActivity.class));

            }

        });


        logOutOfAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent( SettingsActivity.this, MainActivity.class));
                finishAffinity();
            }
        });


        outOfSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent( SettingsActivity.this, ExploreActivity.class));
            }
        });



    }



}