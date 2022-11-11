package com.example.studentservicerequester;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class RecycleViewAdapterActivity extends RecyclerView.Adapter <RecyclerView.ViewHolder> {

    Context context;
    ArrayList<StudentService>  list = new ArrayList<>();
    public RecycleViewAdapterActivity(Context ctx){

        this.context = ctx;
    }
    public void setItems(ArrayList<StudentService> fog){

        list.addAll(fog);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent, false);
        return new StudentServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        StudentServiceViewHolder vh =(StudentServiceViewHolder) holder;
        StudentService std = list.get(position);
        vh.txt_school.setText(std.getSchool());
        vh.txt_grade.setText(std.getGrade());
        vh.txt_noStd.setText(std.getNoStd());
        vh.txt_essentials.setText(std.getEssentials());
        vh.txt_phone.setText(std.getPhone());
        vh.txt_option.setOnClickListener(v->{

            PopupMenu popupMenu = new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->{

                switch (item.getItemId()){

                    case R.id.menu_edit:  //update button

                        Intent intent = new Intent(context,AddRequestActivity.class);
                        intent.putExtra("key",std.getKey());

                        intent.putExtra("school",std.getSchool());
                        intent.putExtra("grade",std.getGrade());
                        intent.putExtra("noStd",std.getNoStd());
                        intent.putExtra("essentials",std.getEssentials());
                        intent.putExtra("phone",std.getPhone());
//                        intent.putExtra("EDIT", std);
                        context.startActivity(intent);
                        break;

                    case R.id.menu_remove:  //delete button

                        DAOStudentService dao = new DAOStudentService();
                        dao.remove(std.getKey()).addOnSuccessListener(suc ->{

                            Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            
                        }).addOnFailureListener(er->{

                            Toast.makeText(context,""+er.getMessage(),Toast.LENGTH_SHORT).show();
                        });
                        break;

                }
                return false;
            });
            popupMenu.show();

        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
