package com.example.studentservicerequester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class AddRequestActivity extends AppCompatActivity {

//    define variables

    TextInputEditText School;
    TextInputEditText Grade;
    TextInputEditText NoStd;
    TextInputEditText Essentials;
    TextInputEditText Phone;
    Button btnReg;
    Button btnOpen;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        School = findViewById(R.id.school);  //find id
        Grade = findViewById(R.id.grade);
        NoStd = findViewById(R.id.noStd);
        Essentials = findViewById(R.id.essentials);
        Phone = findViewById(R.id.phone);
        btnReg = findViewById(R.id.btnReg);

        btnOpen = findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(v-> {

            Intent intent =  new Intent(AddRequestActivity.this, RecycleViewActivity.class);
            startActivity(intent);

        });

        back = findViewById(R.id.btnLogout);
        DAOStudentService dao = new DAOStudentService();
        StudentService std_edit =(StudentService)getIntent().getSerializableExtra("EDIT");

        Intent i = getIntent();
        //get data from the intent
        String key = i.getStringExtra("key");
        String school = i.getStringExtra("school");
        String grade = i.getStringExtra("grade");
        String noStd = i.getStringExtra("noStd");
        String essentials =i.getStringExtra("essentials");
        String phone = i.getStringExtra("phone");


        if(school != null){

            btnReg.setText("UPDATE");
            School.setText(school);
            Grade.setText(grade);
            NoStd.setText(noStd);
            Essentials.setText(essentials);
            Phone.setText(phone);

            btnOpen.setVisibility(View.GONE);
        }
        else {

            btnReg.setText("SUBMIT");
            btnOpen.setVisibility(View.VISIBLE);
        }


        back.setOnClickListener(view ->{
            startActivity(new Intent(AddRequestActivity.this, MainActivity.class));
        });



        btnReg.setOnClickListener(v-> {

            StudentService fog = new StudentService(School.getText().toString(), Grade.getText().toString(), NoStd.getText().toString(), Essentials.getText().toString(), Phone.getText().toString());

               String data = School.getText().toString();
               String data1 = Grade.getText().toString();
               String data2 = NoStd.getText().toString();
               String data3 = Essentials.getText().toString();
               String data4 = Phone.getText().toString();

               //check the details are empty

               if(data.isEmpty()){
                   Toast.makeText(AddRequestActivity.this,"Enter the school name please!",Toast.LENGTH_SHORT).show();
               }else if(data1.isEmpty()){
                   Toast.makeText(AddRequestActivity.this,"Enter the grade of those students please!",Toast.LENGTH_SHORT).show();
               }else if(data2.isEmpty()){
                   Toast.makeText(AddRequestActivity.this,"Enter the student count please!",Toast.LENGTH_SHORT).show();
               }else if(data3.isEmpty()){
                   Toast.makeText(AddRequestActivity.this,"Enter the essentials please!",Toast.LENGTH_SHORT).show();
               }else if(data4.isEmpty()){
                   Toast.makeText(AddRequestActivity.this,"Enter the contact number please!",Toast.LENGTH_SHORT).show();
               }else {

            if (school == null) {  //insert details

                dao.add(fog).addOnSuccessListener(suc -> {

                    Toast.makeText(this, "Details are inserted", Toast.LENGTH_SHORT).show();

                }).addOnFailureListener(er -> {

                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });

            } else {

                //update details

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("school", School.getText().toString());
                hashMap.put("grade", Grade.getText().toString());
                hashMap.put("noStd", NoStd.getText().toString());
                hashMap.put("essentials", Essentials.getText().toString());
                hashMap.put("phone", Phone.getText().toString());
                dao.update(key, hashMap).addOnSuccessListener(suc -> {

                    Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er -> {

                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        }

        });


    }
}