package com.example.studentsapp.model;


import com.example.studentsapp.MyApplication;
import java.util.LinkedList;
import java.util.List;

public class Model {
    static final private Model instance = new Model();

    private  Model(){
    }
    public static Model getInstance(){
        return  instance;
    }

    public interface GetAllStudentsListener{
        void onComplete(List<Student> data);
    }

    public void getStudentList(GetAllStudentsListener listener){
        MyApplication.executorService.execute(()-> {
            List<Student> data = AppLocalDB.db.studentDao().getAll();
            MyApplication.mainHandler.post(()-> {
                listener.onComplete(data);
            });
        });
    }
    public interface addNewStudentListener{
        void onComplete();
    }
    public void addNewStudent(Student student, addNewStudentListener listener){
        MyApplication.executorService.execute(()->{
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            AppLocalDB.db.studentDao().insertAll(student);
            MyApplication.mainHandler.post(()->{
                listener.onComplete();
            });
        });

    }

    public void deleteStudent(Student student){
        AppLocalDB.db.studentDao().delete(student);
        //dataB.remove(student);
    }
    public interface getStudentByIDListener{
        void onComplete(Student s);
    }

    public void getStudentByID(String id, getStudentByIDListener listener){
        MyApplication.executorService.execute(()->{
            Student s = AppLocalDB.db.studentDao().getStudentByID(id);
            MyApplication.mainHandler.post(()->{
                listener.onComplete(s);
            });
        });
    }
}
