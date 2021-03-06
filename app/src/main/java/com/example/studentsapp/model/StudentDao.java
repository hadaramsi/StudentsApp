package com.example.studentsapp.model;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("select * from Student")
    List<Student> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Student... students);

    @Delete
    void delete(Student student);

    @Query("SELECT * FROM Student WHERE id=:id")
    Student getStudentByID(String id);

    @Update
    void editStudent(Student s);

}
