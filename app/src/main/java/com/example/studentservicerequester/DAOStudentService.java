package com.example.studentservicerequester;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOStudentService {

    DatabaseReference databaseReference;

    public DAOStudentService(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(StudentService.class.getSimpleName());

    }
    public Task<Void> add(StudentService fog){

      return   databaseReference.push().setValue(fog);
    }

}
