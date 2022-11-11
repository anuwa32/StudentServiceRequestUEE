package com.example.studentservicerequester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button btnRequester;
    Button btnProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnRequester = findViewById(R.id.btnRequester);
//        btnProvider = findViewById(R.id.btnProvider);

        btnRequester.setOnClickListener(v->{
            Intent intent =  new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }
}