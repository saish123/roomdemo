package com.sawant.room_demo.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sawant.room_demo.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/18/18.
 */
@Dao
public interface StudentDao {
    @Query("select * from Student")
    LiveData<List<Student>> getAllStudent();

    @Query("select * from Student where studentId = :studentId")
    Student getStudentByid(int studentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addStudent(Student student);

    @Delete
    void deleteStudent(Student student);
}
