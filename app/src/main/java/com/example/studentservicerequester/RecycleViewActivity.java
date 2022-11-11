package com.example.studentservicerequester;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecycleViewActivity extends AppCompatActivity {


    SwipeRefreshLayout swipeRefreshLayout;  //Define variables
    RecyclerView recyclerView;
    RecycleViewAdapterActivity adapter;
    DAOStudentService dao;
    boolean isLoading = false;
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        swipeRefreshLayout = findViewById(R.id.swip);  //find the reference id
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new RecycleViewAdapterActivity(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOStudentService();
        loadData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem =  linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(totalItem < lastVisible+3){

                    if(!isLoading){
                        isLoading = true;
                        loadData();
                    }
                }

            }
        });

    }

    private void loadData() {

        swipeRefreshLayout.setRefreshing(true);

        dao.get(key).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<StudentService> stds = new ArrayList<>();

                 for(DataSnapshot data : snapshot.getChildren()){

                    StudentService std = data.getValue(StudentService.class);
                    std.setKey(data.getKey());
                    stds.add(std);
                    key = data.getKey();

                 }
                 adapter.setItems(stds);
                 adapter.notifyDataSetChanged();
                 isLoading = false;
                 swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
}