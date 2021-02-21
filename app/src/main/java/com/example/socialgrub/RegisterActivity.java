package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    private TextView registerUser;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextUsername, editTextPassword;


    private FirebaseAuth mAuth;




    // change password to text password
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        Button backbutton = (Button) findViewById(R.id.backButton);
        Button registerButton = (Button) findViewById(R.id.confirmRegister);

        editTextFirstName = (EditText) findViewById(R.id.registerEnterName);
        editTextLastName = (EditText) findViewById(R.id.editLastname);
        editTextEmail = (EditText) findViewById(R.id.enterEmail);
        editTextUsername = (EditText) findViewById(R.id.registerEnterUsername);
        editTextPassword = (EditText) findViewById(R.id.registerEnterPassword);




        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });



    }

    private void registerUser() {

        int requiredPasswordLength = 6;

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String userName = editTextUsername.getText().toString().trim();


        if (firstName.isEmpty())
        {
            editTextFirstName.setError("First name required");
            editTextFirstName.requestFocus();
            return;
        }

        if (lastName.isEmpty())
        {
            editTextLastName.setError("Last name required");
            editTextLastName.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }
        // Enforces a proper email format
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextEmail.setError("Valid email required");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < requiredPasswordLength)
        {
            editTextPassword.setError("Password must be at least " + requiredPasswordLength + " characters long");
            editTextPassword.requestFocus();
            return;
        }


        if (userName.isEmpty())
        {
            editTextUsername.setError("Username required");
            editTextUsername.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User user = new User(firstName, lastName, email, userName);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "User has been registered successfully", Toast.LENGTH_LONG).show();





                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterActivity.this, "Failed to register user", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Failed to register user", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}