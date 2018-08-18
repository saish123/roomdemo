package com.sawant.room_demo;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sawant.room_demo.adapter.StudentRecyclerAdapter;
import com.sawant.room_demo.entity.Student;
import com.sawant.room_demo.viewmodel.StudentDataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.editTextStudentId)
    EditText editTextStudentId;
    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.editTextAddress)
    EditText editTextAddress;
    @BindView(R.id.buttonAdd)
    Button buttonAdd;
    @BindView(R.id.buttonDelete)
    Button buttonDelete;
    @BindView(R.id.buttonFetch)
    Button buttonFetch;

    @BindView(R.id.studentRecyclerView)
    RecyclerView recyclerView;
    private StudentDataModel model;
    private StudentRecyclerAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeResources();


    }

    private void initializeResources() {
        model = ViewModelProviders.of(this).get(StudentDataModel.class);
        buttonAdd.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonFetch.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:
                int studentId = 0;
                String studentName = "";
                String studentAddress = "";
                if (editTextStudentId != null && !TextUtils.isEmpty(editTextStudentId.getText().toString())) {
                    studentId = Integer.parseInt(editTextStudentId.getText().toString());
                }
                if (editTextName != null && !TextUtils.isEmpty(editTextName.getText().toString())) {
                    studentName = editTextName.getText().toString();
                }
                if (editTextAddress != null && !TextUtils.isEmpty(editTextAddress.getText().toString())) {
                    studentAddress = editTextAddress.getText().toString();
                }
                model.addStudent(new Student(studentId, studentName, studentAddress));
                break;
            case R.id.buttonDelete:

                int id = 0;
                String name = "";
                String address = "";
                if (editTextStudentId != null && !TextUtils.isEmpty(editTextStudentId.getText().toString())) {
                    id = Integer.parseInt(editTextStudentId.getText().toString());
                }
                if (editTextName != null && !TextUtils.isEmpty(editTextName.getText().toString())) {
                    name = editTextName.getText().toString();
                }
                if (editTextAddress != null && !TextUtils.isEmpty(editTextAddress.getText().toString())) {
                    address = editTextAddress.getText().toString();
                }
                model.deleteStudent(new Student(id, name, address));
                break;
            case R.id.buttonFetch:
                model.getAllStudent().observe(this, studentList -> {

//                    for (int i = 0; i < githubUserEntityModelList.size(); i++) {
//                        Log.d("name ", githubUserEntityModelList.get(i).getStudentName() + " ");
//                    }
                    setAdapter(studentList);

                });
                break;

        }
    }

    private void setAdapter(List<Student> studentList) {
        if (studentAdapter == null) {
            studentAdapter = new StudentRecyclerAdapter(this, studentList);
            recyclerView.setAdapter(studentAdapter);
        } else {
            studentAdapter.notifyStudetDataset(studentList);
        }
    }
}
