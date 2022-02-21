package com.example.smarttravelguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class spotForm extends AppCompatActivity {
EditText title, description, weather, latitude, longitude;
Button save, browse;
String videoUrl;
ProgressDialog progressDialog;
ProgressBar progressBar;
DatabaseReference databaseReference;
StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_form);

        title = findViewById(R.id.spotTitle);
        description = findViewById(R.id.spotDescription);
        weather = findViewById(R.id.spotWeather);
        latitude = findViewById(R.id.spotLatitude);
        longitude = findViewById(R.id.spotLongitude);
        save = findViewById(R.id.button3);
        browse = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Tourist_Spot");

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseVideo();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadvideo();
            }
        });
    }
    // choose a video from phone storage
    private void chooseVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 5);
    }
    Uri videouri;
    // startActivityForResult is used to receive the result, which is the selected video.
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            videouri = data.getData();
        }
    }
    private String getfiletype(Uri videouri) {
        ContentResolver r = getContentResolver();
        // get the file type ,in this case its mp4
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(r.getType(videouri));
    }

    private void uploadvideo() {
        if (videouri != null) {
            // save the selected video in Firebase storage
            storageReference = FirebaseStorage.getInstance().getReference("Videos/" + System.currentTimeMillis() + "." + getfiletype(videouri));
            storageReference.putFile(videouri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    progressDialog.dismiss();
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String t = title.getText().toString(),
                                    w = weather.getText().toString(),
                                    d = description.getText().toString();
                            double lat = Double.parseDouble(latitude.getText().toString()),
                                    lng = Double.parseDouble(longitude.getText().toString());
                            touristSpot touristSpot = new touristSpot(t,d,w,lat,lng,uri.toString());
                            databaseReference.child(touristSpot.getTitle()).setValue(touristSpot).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(spotForm.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(spotForm.this, "Unsuccessfully submitted", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
//                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
//                    while (!uriTask.isSuccessful()) {
//                        // get the link of video
//                        videoUrl = uriTask.getResult().toString();
//                        Log.i("Video link", videoUrl);
//                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Error, Image not uploaded
//                    Toast.makeText(MainActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                    float percentage = (snapshot.getBytesTransferred()*100)/snapshot.getTotalByteCount();
//                        progressDialog.setMessage(percentage + "% Completed");
//                        progressDialog.show();
//                          progressBar.setProgress((int)percentage);
                }
            });
        }
    }
}