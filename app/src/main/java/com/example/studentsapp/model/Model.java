package com.example.studentsapp.model;


import java.util.LinkedList;
import java.util.List;

public class Model {
    static final private Model instance = new Model();
    private List<Student> dataB = new LinkedList<Student>();

    private  Model(){
    }
    public static Model getInstance(){
        return  instance;
    }


    public List<Student> getStudentList(){
        return dataB;
    }

    public void addNewStudent(Student student){
        dataB.add(student);
    }

    public void deleteStudent(Student student){
        dataB.remove(student);
    }

    public Student getStudentByID(String id){
        for( Student s: dataB) {
            if (s.getStudentID().equals(id))
                return s;
        }
        return null;
    }
}
