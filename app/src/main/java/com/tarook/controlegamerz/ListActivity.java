package com.tarook.controlegamerz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.profileListView);
        addButton = findViewById(R.id.add_button);
        loadFromDBToMemory();

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        ProfilAdapter adapter = new ProfilAdapter(getApplicationContext(), Profile.profiles);
        listView.setAdapter(adapter);
    }

    public void loadFromDBToMemory(){
        SQLiteManager sqLiteManager = SQLiteManager.getInstance(this);
        // clear list then populate it
        Profile.profiles.clear();
        sqLiteManager.populateList();
    }
}