package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class adminDashboard extends AppCompatActivity {
String stringLabel;
ImageButton addSpot;
Button signOut, listSpot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Intent iget = getIntent();
        stringLabel = iget.getStringExtra("U");
        addSpot = findViewById(R.id.imageButton);
        signOut =findViewById(R.id.button4);
        listSpot =findViewById(R.id.button5);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(adminDashboard.this,AdminLogIn.class);
                startActivity(i);
            }
        });

        listSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(adminDashboard.this,spotRecycler.class);
                startActivity(i);
            }
        });

        addSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),spotForm.class));
            }
        });
    }
}