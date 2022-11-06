package com.example.studentservicerequester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddRequestActivity extends AppCompatActivity {

    TextInputEditText School;
    TextInputEditText Grade;
    TextInputEditText NoStd;
    TextInputEditText Essentials;
    Button btnReg;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        School = findViewById(R.id.school);
        Grade = findViewById(R.id.grade);
        NoStd = findViewById(R.id.noStd);
        Essentials = findViewById(R.id.essentials);
        btnReg = findViewById(R.id.btnReg);
        back = findViewById(R.id.btnLogout);
        DAOStudentService dao = new DAOStudentService();

        back.setOnClickListener(view ->{
            startActivity(new Intent(AddRequestActivity.this, MainActivity.class));
        });

        btnReg.setOnClickListener(v->{

           StudentService fog = new StudentService(School.getText().toString(),Grade.getText().toString(),NoStd.getText().toString(),Essentials.getText().toString());
           dao.add(fog).addOnSuccessListener(suc ->{

               Toast.makeText(this, "Details are inserted",Toast.LENGTH_SHORT).show();

           }).addOnFailureListener(er -> {

               Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
           });

        });

    }
}