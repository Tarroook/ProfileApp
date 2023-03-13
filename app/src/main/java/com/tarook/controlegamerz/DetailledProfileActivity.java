package com.tarook.controlegamerz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailledProfileActivity extends AppCompatActivity {

    private TextView nom, adresse, email, age;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailled_profile);

        nom = findViewById(R.id.nom);
        adresse = findViewById(R.id.adresse);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);

        Intent intent = getIntent();
        String getName = intent.getStringExtra("nom");
        String getAdresse = intent.getStringExtra("adresse");
        String getEmail = intent.getStringExtra("email");
        String getAge = intent.getStringExtra("age");

        nom.setText(getName);
        adresse.setText(getAdresse);
        email.setText(getEmail);
        age.setText(getAge);

    }
}