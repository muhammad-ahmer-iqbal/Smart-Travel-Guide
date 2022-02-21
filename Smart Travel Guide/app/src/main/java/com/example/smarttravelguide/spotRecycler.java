package com.example.smarttravelguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.login.LoginException;

public class spotRecycler extends AppCompatActivity {
RecyclerView recyclerView;
List<touristSpot> touristSpotList;
touristSportAdapter touristSportAdapter;
DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_recycler);
        Log.i("msg","success");
        touristSpotList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Tourist_Spot");
        //databaseReference = firebaseDatabase.getReference("Tourist_Spot");

        fetchData();
    }

    public void fetchData() {

        // Read from the database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                touristSpotList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Log.i("Error", String.valueOf(postSnapshot.getValue()));
                    HashMap<String,String> map=(HashMap<String, String>) postSnapshot.getValue();
                    HashMap<String, Long> map1=(HashMap<String, Long>) postSnapshot.getValue();
//                    touristSpotList.add(new touristSpot(map.get("title"),map.get("description"),map.get("weather"),map.get("video")));
                    touristSpotList.add(new touristSpot(map.get("title"),map.get("description"),map.get("weather"),map1.get("latitude"),map1.get("longitude"),map.get("video")));
                }

                touristSportAdapter = new touristSportAdapter(spotRecycler.this,touristSpotList);

                recyclerView.setAdapter(touristSportAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}