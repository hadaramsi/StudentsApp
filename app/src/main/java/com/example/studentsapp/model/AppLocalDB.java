package com.example.studentsapp.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.studentsapp.MyApplication;

@Database(entities = {Student.class}, version = 3)
abstract class AppLocalDbRepository extends RoomDatabase {
    public abstract StudentDao studentDao();
}
public class AppLocalDB {
    static public final AppLocalDbRepository db =
            Room.databaseBuilder(MyApplication.getContext(),
                    AppLocalDbRepository.class,
                    "dbFileName.db")
                    .fallbackToDestructiveMigration()
                    .build();
    private AppLocalDB(){}
}

