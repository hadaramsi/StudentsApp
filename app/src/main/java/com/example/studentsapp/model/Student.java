package com.example.studentsapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class Student {
    @PrimaryKey
    @NonNull
    String name;
    String id;
    String address;
    String phone;
    boolean cb;

    public Student(){
//        name= "";
//        id= "";
//        cb = false;
    }
    public Student(String name, String id, String phone, String address, boolean cb){
        this.name= name;
        this.id= id;
        this.phone=phone;
        this.address=address;
        this.cb = cb;
    }

    public String getStudentName(){
        return name;
    }
    public String getStudentID(){
        return id;
    }
    public boolean getStudentCB(){
        return cb;
    }
    public String getStudentPhone(){
        return phone;
    }
    public String getStudentAddress(){
        return address;
    }
    public void setStudentCB(boolean cb){
        this.cb=cb;
    }
    public void setStudentName(String name){
        this.name=name;
    }
    public void setStudentID(String id){
        this.id=id;
    }
    public void setStudentPhone(String phone){
        this.phone=phone;
    }
    public void setStudentAddress(String address){
        this.address=address;
    }
}
