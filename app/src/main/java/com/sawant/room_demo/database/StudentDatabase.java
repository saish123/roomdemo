package com.sawant.room_demo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.sawant.room_demo.dao.StudentDao;
import com.sawant.room_demo.entity.Student;

/**
 * Created by root on 8/18/18.
 */
@Database(entities = {Student.class},version =  1,exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    private static StudentDatabase instance;

    public static StudentDatabase getDatabase(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),StudentDatabase.class,"test_db").build();
        }
        return instance;
    }

    public abstract StudentDao getStudentDao();
}
