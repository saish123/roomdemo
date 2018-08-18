package com.sawant.room_demo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.sawant.room_demo.database.StudentDatabase;
import com.sawant.room_demo.entity.Student;

import java.util.List;

/**
 * Created by root on 8/18/18.
 */

public class StudentDataModel extends AndroidViewModel {

    private final StudentDatabase appDatabase;

    public StudentDataModel(@NonNull Application application) {
        super(application);
        appDatabase = StudentDatabase.getDatabase(this.getApplication());
    }


    public LiveData<List<Student>> getAllStudent() {
        return appDatabase.getStudentDao().getAllStudent();
    }

    public void addStudent(Student student) {
        new AddStudentAsyncTask(appDatabase).execute(student);
    }

    public void deleteStudent(Student student) {
        new DeleteStudentAsyncTask(appDatabase).execute(student);
    }

    private static class AddStudentAsyncTask extends AsyncTask<Student, Void, Void> {

        private StudentDatabase database;

        private AddStudentAsyncTask(StudentDatabase appDatabase) {
            this.database = appDatabase;
        }

        @Override
        protected Void doInBackground(Student... githubUserEntityModels) {
            Long id = database.getStudentDao().addStudent(githubUserEntityModels[0]);
            return null;
        }
    }


    private static class DeleteStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        StudentDatabase database;

        private DeleteStudentAsyncTask(StudentDatabase appDatabase) {
            this.database = appDatabase;
        }

        @Override
        protected Void doInBackground(Student... student) {
            database.getStudentDao().deleteStudent(student[0]);
            return null;
        }
    }


}

