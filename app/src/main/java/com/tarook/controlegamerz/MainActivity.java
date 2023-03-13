package com.tarook.controlegamerz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        enableSaveButton();
        setupListeners();
    }



    private void enableSaveButton() {
        saveButton.setEnabled(!nameEditText.getText().toString().isEmpty()
                && !ageEditText.getText().toString().isEmpty()
                && !addressEditText.getText().toString().isEmpty()
                && !emailEditText.getText().toString().isEmpty()
                && picture != null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            assert data != null;
            picture = (Bitmap) data.getExtras().get("data");
            pictureStatusText.setText("Photo prise");
            enableSaveButton();
        }
    }

    private void saveNote() {
        SQLiteManager sqLiteManager = SQLiteManager.getInstance(this);
        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String email = emailEditText.getText().toString();
        int id = Profile.profiles.size();

        Profile profile = new Profile(id, name, age, address, email, picture);
        sqLiteManager.addProfile(profile);

        // disable all the fields
        saveButton.setEnabled(false);
        takePictureButton.setEnabled(false);
        nameEditText.setEnabled(false);
        ageEditText.setEnabled(false);
        addressEditText.setEnabled(false);
        emailEditText.setEnabled(false);

        // Notify the user
        Toast.makeText(this, "Profil sauvegardé", Toast.LENGTH_SHORT).show();

        //clearFields();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openList();
            }
        }, 1000);

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

    private void setupListeners() {
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

        // on change of text in all edit texts, enable the save button if all fields are filled

        nameEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSaveButton();
            }
        });

        ageEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSaveButton();
            }
        });

        addressEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSaveButton();
            }
        });

        emailEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSaveButton();
            }
        });
    }
}