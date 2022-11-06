package com.example.studentservicerequester;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnLogOut;
    Button btnAddReq;
    FirebaseAuth fBAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogOut = findViewById(R.id.btnLogout);
        btnAddReq = findViewById(R.id.btnAddReq);
        fBAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view ->{
            fBAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        btnAddReq.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddRequestActivity.class));
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = fBAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}