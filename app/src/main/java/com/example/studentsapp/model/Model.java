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

    public interface deleteStudentListener{
        void onComplete();
    }
    public void deleteStudent(Student student, deleteStudentListener listener){
        MyApplication.executorService.execute(()->{
            AppLocalDB.db.studentDao().delete(student);
            MyApplication.mainHandler.post(()->{
                listener.onComplete();
            });
        });
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
    public interface editStudentListener{
        void onComplete();
    }

    public void editStudent(Student s, editStudentListener listener){
        MyApplication.executorService.execute(()->{
//            s.setStudentName((newS.getStudentName()));
//            s.setStudentPhone(newS.getStudentPhone());
//            s.setStudentAddress(newS.getStudentAddress());
//            s.setStudentCB(newS.getStudentCB());
            AppLocalDB.db.studentDao().editStudent(s);
            MyApplication.mainHandler.post(()->{
                listener.onComplete();
            });
        });
    }
}
