package com.sawant.room_demo;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sawant.room_demo.entity.Student;
import com.sawant.room_demo.viewmodel.StudentDataModel;

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
    private StudentDataModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeResources();


    }

    private void initializeResources() {


        buttonAdd.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonFetch.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:
                int studentId=0;
                String studentName="";
                String studentAddress="";
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

                int id=0;
                String name ="";
                String address ="";
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
                model = ViewModelProviders.of(this).get(StudentDataModel.class);
                model.getAllStudent().observe(this, githubUserEntityModelList -> {

                    for (int i = 0; i < githubUserEntityModelList.size(); i++) {
                        Log.d("name ", githubUserEntityModelList.get(i).getStudentName() + " ");
                    }

                });
                break;

        }
    }
}
