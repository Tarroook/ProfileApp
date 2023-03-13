package com.tarook.controlegamerz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailledProfileActivity extends AppCompatActivity {

    private TextView nom, adresse, email, age;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailled_profile);

        nom = findViewById(R.id.nom);
        adresse = findViewById(R.id.adresse);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        image = findViewById(R.id.imageview);

        Intent intent = getIntent();
        String getName = intent.getStringExtra("nom");
        String getAdresse = intent.getStringExtra("adresse");
        String getEmail = intent.getStringExtra("email");
        String getAge = intent.getStringExtra("age");
        Bitmap getImage = intent.getParcelableExtra("image");

        nom.setText(getName);
        adresse.setText(getAdresse);
        email.setText(getEmail);
        age.setText(getAge);
        image.setImageBitmap(getImage);

    }
}