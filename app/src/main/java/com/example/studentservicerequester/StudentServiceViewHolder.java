package com.example.studentservicerequester;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentServiceViewHolder extends RecyclerView.ViewHolder {

    TextView txt_school;  //define text views
    TextView txt_grade;
    TextView txt_noStd;
    TextView txt_essentials;
    TextView txt_phone;
    TextView txt_option;

    public StudentServiceViewHolder(@NonNull View itemView) {

        super(itemView);
        txt_school = itemView.findViewById(R.id.txt_school);  //find the reference id by itemView
        txt_grade = itemView.findViewById(R.id.txt_grade);
        txt_noStd = itemView.findViewById(R.id.txt_noStd);
        txt_essentials = itemView.findViewById(R.id.txt_essentials);
        txt_phone = itemView.findViewById(R.id.txt_phone);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
