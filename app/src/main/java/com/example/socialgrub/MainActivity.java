package com.example.socialgrub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button registerBtn;
    Button forgotPass;
    EditText editTextEmail;
    EditText editTextPassword;


    private FirebaseAuth mAuth;
    //ProgressBar progressBar;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerBtn = (Button) findViewById(R.id.register_button);
        registerBtn.setOnClickListener(this);

        Button signInBtn = (Button) findViewById(R.id.sign_in);
        signInBtn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.enter_email);
        editTextPassword = (EditText) findViewById(R.id.enter_password);

        mAuth = FirebaseAuth.getInstance();

        forgotPass = findViewById(R.id.forgotPassword);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgotPasswordActivity.class));
            }
        });

    }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.register_button:


                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;

                case R.id.sign_in:
                    userLogin();
                    break;


            }
        }

        private void userLogin() {

                int passwordReqLength = 6;

                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if(email.isEmpty()) {
                    editTextEmail.setError("Email is required!");
                    editTextEmail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                    editTextEmail.setError("Please enter a valid email");
                    editTextEmail.requestFocus();
                }

                if(password.isEmpty()) {

                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                    return;
                }

                if(password.length() < passwordReqLength) {

                    editTextPassword.setError("Minimum password length is six characters");
                    editTextPassword.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {

                            //redirect to user profile
                            startActivity(new Intent(MainActivity.this, ExploreActivity.class));
                        }

                        else {

                            Toast.makeText(MainActivity.this, "Failed to login, Please check your credentials", Toast.LENGTH_LONG).show();
                        }

                    }

            });

            }



        }


