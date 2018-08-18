package com.sawant.room_demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sawant.room_demo.R;
import com.sawant.room_demo.entity.Student;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 8/18/18.
 */

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private Context context;
    private View view;


    public StudentRecyclerAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        if (student != null) {

            holder.textViewStudentId.setText(student.getStudentId()+" ");
            holder.textViewStudentName.setText(student.getStudentName());
            holder.textViewStudentAddress.setText(student.getStudentAddress());
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void notifyStudetDataset(List<Student> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewStudentId)
        TextView textViewStudentId;
        @BindView(R.id.textViewName)
        TextView textViewStudentName;
        @BindView(R.id.textViewAddress)
        TextView textViewStudentAddress;

        public StudentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
