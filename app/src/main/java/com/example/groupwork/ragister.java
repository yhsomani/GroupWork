package com.example.groupwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ragister extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText ragisterEmail, ragisterPass;
//    EditText ragisterName;
    Button ragiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragister);
        mAuth = FirebaseAuth.getInstance();

//        ragisterName = (EditText)findViewById(R.id.ragister_Name);
        ragisterEmail = (EditText)findViewById(R.id.ragister_EmailAddress);
        ragisterPass = (EditText)findViewById(R.id.ragister_Password);
        ragiter = (Button)findViewById(R.id.btn_ragister);



//        Transparent ActionBar{
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }
        getSupportActionBar().hide();

        ragiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, name;
                email = ragisterEmail.getText().toString();
                password = ragisterPass.getText().toString();
//                name = ragisterName.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d("TAG", "createUserWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    updateUI(user);
                                    startActivity(new Intent(ragister.this, MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(ragister.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
//        startActivity(new Intent(ragister.this, MainActivity.class));
    }

}