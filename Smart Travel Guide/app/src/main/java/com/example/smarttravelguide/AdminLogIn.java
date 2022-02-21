package com.example.smarttravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogIn extends AppCompatActivity {
TextView uId, uPassword;
Button signIn;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log_in);

        uId = findViewById(R.id.editTextTextEmailAddress);
        uPassword = findViewById(R.id.editTextTextPassword);
        signIn = findViewById(R.id.button);
        firebaseAuth = FirebaseAuth.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(uId.getText().toString(),uPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(AdminLogIn.this, "Logged in", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), adminDashboard.class);
                        i.putExtra("U",uId.getText().toString());
//                        Toast.makeText(AdminLogIn.this, uId.getText().toString(), Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminLogIn.this, "Unsuccessfull logged in", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
//        https://www.androidhive.info/2016/06/android-getting-started-firebase-simple-login-registration-auth/
    }
}