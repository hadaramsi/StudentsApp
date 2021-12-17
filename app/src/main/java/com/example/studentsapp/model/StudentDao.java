package com.example.studentsapp.model;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface StudentDao {
    @Query("select * from Student")
    List<Studnt> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Student... students);

    @Delete
    void delete(Student student);

}
