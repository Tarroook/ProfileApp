package com.tarook.controlegamerz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private Button takePictureButton;
    private TextView pictureStatusText;
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText addressEditText;
    private EditText emailEditText;
    private Bitmap picture;
    private Button listButton;


    // Une activité qui permet à l'utilisateur de créer un nouveau profil dans la base de données ainsi que de sauvegarder des informations personnelles relatives (photo, nom, adresse)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    android.Manifest.permission.CAMERA
      }, 100);
        }

        takePictureButton.setOnClickListener(v -> {
            // opens the camera and takes a picture
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 100);
        });

        saveButton.setOnClickListener(v -> {
            // enters the data into the database, the database is just a text file for now
            saveNote();
        });

        listButton.setOnClickListener(v -> {
            openList();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            assert data != null;
            picture = (Bitmap) data.getExtras().get("data");
            pictureStatusText.setText("Photo prise");
        }
    }

    private void saveNote() {
        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String email = emailEditText.getText().toString();

        Profile profile = new Profile(name, age, address, email, picture);
        Profile.profiles.add(profile);
        clearFields();
        openList();
    }

    private void clearFields() {
        nameEditText.setText("");
        ageEditText.setText("");
        addressEditText.setText("");
        emailEditText.setText("");
        picture = null;
        pictureStatusText.setText("Aucune photo prise");
    }

    private void setupViews() {
        saveButton = findViewById(R.id.buttonSave);
        takePictureButton = findViewById(R.id.buttonPhoto);
        pictureStatusText = findViewById(R.id.textPhoto);
        nameEditText = findViewById(R.id.editTextName);
        ageEditText = findViewById(R.id.editTextAge);
        addressEditText = findViewById(R.id.editTextAddress);
        emailEditText = findViewById(R.id.editTextEmail);
        listButton = findViewById(R.id.buttonList);
    }

    public void openList(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}