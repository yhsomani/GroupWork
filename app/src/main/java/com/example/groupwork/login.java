package com.example.groupwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class login extends AppCompatActivity {

    Button Btn_login, Btn_ragister;
    EditText emailBox, passwordBox;
    private FirebaseAuth auth; //firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Transparent ActionBar{
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
        getSupportActionBar().hide();

        Btn_login = (Button) findViewById(R.id.btn_login);
        Btn_ragister = (Button) findViewById(R.id.btn_ragister2);
        emailBox = (EditText) findViewById(R.id.login_EmailAddress);
        passwordBox = (EditText) findViewById(R.id.login_Password);

        auth = FirebaseAuth.getInstance(); //firebase

        Btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, password;
                email = emailBox.getText().toString();
                password = passwordBox.getText().toString();
                if (email.trim().length() > 0 && password.trim().length() > 0) {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this, MainActivity.class));
                            } else {
                                Toast.makeText(login.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(login.this, "Enter Email and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Btn_ragister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, ragister.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = auth.getCurrentUser();
//        updateUI(currentUser);
    }

//    private void updateUI(FirebaseUser currentUser) {
//        startActivity(new Intent(login.this, MainActivity.class));
//    }
}