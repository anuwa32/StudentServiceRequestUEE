package com.example.studentservicerequester;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    TextInputEditText CusForgotPass;
    Button btnForgot;
    ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        CusForgotPass = findViewById(R.id.email);
        btnForgot = findViewById(R.id.btnForgot);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resetPassword();
            }
        });

    }
    private void resetPassword(){
        String email = CusForgotPass.getText().toString().trim();

        if(email.isEmpty()){
            CusForgotPass.setError("Email cannot be empty field!");
            CusForgotPass.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           CusForgotPass.setError("Please enter valid email!");
           CusForgotPass.requestFocus();
           return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Check your email to reset your password!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ForgotPasswordActivity.this, "Please try again!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}