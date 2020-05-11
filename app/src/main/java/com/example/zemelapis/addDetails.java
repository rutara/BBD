package com.example.zemelapis;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class addDetails extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView photo;
    Button cameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        photo = findViewById(R.id.phototaken);
        cameraBtn = findViewById(R.id.cambtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(addDetails.this, "Camera button is clicked", Toast.LENGTH_SHORT).show();
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }

//
//        @Override
//        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
//            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                photo.setImageBitmap(imageBitmap);
//            }
//        }
    });

};
}




