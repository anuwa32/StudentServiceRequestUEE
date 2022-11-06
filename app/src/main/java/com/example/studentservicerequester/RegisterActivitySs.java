package com.example.studentservicerequester;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivitySs extends AppCompatActivity {

    TextInputEditText CusEmail;
    TextInputEditText CusPassword;
    TextView CusLogin;
    Button btnReg;
    FirebaseAuth fBAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ss);


        CusEmail = findViewById(R.id.CusEmail);
        CusPassword = findViewById(R.id.CusPass);
        CusLogin = findViewById(R.id.CusLogin);
        btnReg = findViewById(R.id.btnReg);

        fBAuth = FirebaseAuth.getInstance();

        btnReg.setOnClickListener(view ->{
            createUser();
        });

        CusLogin.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivitySs.this, LoginActivity.class));
        });
    }


    private void createUser(){
        String email = CusEmail.getText().toString();
        String password = CusPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            CusEmail.setError("Email cannot be empty field");
            CusEmail.requestFocus();

        }else if (TextUtils.isEmpty(password)){
            CusPassword.setError("Password cannot be empty field");
            CusPassword.requestFocus();

        }else{
            fBAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivitySs.this, "Customer registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivitySs.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivitySs.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}