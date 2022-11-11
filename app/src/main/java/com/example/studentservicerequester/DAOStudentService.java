package com.example.studentservicerequester;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOStudentService {

    DatabaseReference databaseReference;

    public DAOStudentService(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(StudentService.class.getSimpleName());

    }
    public Task<Void> add(StudentService fog){  //add request details

      return   databaseReference.push().setValue(fog);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap)  //update request details
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){  //delete requests

        return databaseReference.child(key).removeValue();
    }

    public Query get(String key){  //retrieve requests details

        if(key == null){
            return databaseReference.orderByKey().limitToFirst(8);
        }

        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

//    public Query get(){
//        return databaseReference;
//    }

}
