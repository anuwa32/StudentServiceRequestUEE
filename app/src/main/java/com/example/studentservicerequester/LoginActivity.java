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

public class LoginActivity extends AppCompatActivity {

    TextInputEditText CusEmail;
    TextInputEditText CusPassword;
    TextView CusRegister;
    TextView CusForgotPassword;
    Button btnLogin;
    Button back;
    FirebaseAuth fBAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        CusEmail = findViewById(R.id.CusEmail);
        CusPassword = findViewById(R.id.CusPassword);
        CusRegister = findViewById(R.id.CusRegister);
        CusForgotPassword = findViewById(R.id.CusForPass);
        btnLogin = findViewById(R.id.btnLogin);
        back = findViewById(R.id.btnBack);

        fBAuth = FirebaseAuth.getInstance();

        back.setOnClickListener(v->{
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        });

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
        CusRegister.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivitySs.class));
        });
        CusForgotPassword.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        });
    }

    private void loginUser(){
        String email = CusEmail.getText().toString();
        String password = CusPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            CusEmail.setError("Email cannot be empty field");
            CusEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            CusPassword.setError("Password cannot be empty field");
            CusPassword.requestFocus();

        }else{
            fBAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Customer logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}