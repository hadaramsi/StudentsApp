package com.example.studentsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.studentsapp.R;
import com.example.studentsapp.StudentDetailsFragmentArgs;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.LinkedList;
import java.util.List;


public class EditStudentFragment extends Fragment {
    Student stu = null; //new Student();
    TextView name;
    TextView id;
    TextView phone;
    TextView address;
    CheckBox cb;
    ProgressBar pb;
    Button saveBt;
    Button deleteBt;
    Button cancelBt;
    View view;

    public EditStudentFragment(){};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_student, container, false);
        name = view.findViewById(R.id.edit_students_name);
        id = view.findViewById(R.id.edit_students_id);
        phone = view.findViewById(R.id.edit_students_phone);
        address = view.findViewById(R.id.edit_students_address);
        cb = view.findViewById(R.id.edit_students_cb);
        pb = view.findViewById(R.id.edit_student_progressBar);
        pb.setVisibility(View.VISIBLE);

        String studentID = EditStudentFragmentArgs.fromBundle(getArguments()).getStudentID();
        Model.getInstance().getStudentByID(studentID, (stu)->{
            setData(stu);
            Log.d("Tag",stu.getStudentID());
//            stu2.setStudentID(stu.getStudentID());
//            stu2.setStudentName(stu.getStudentName());
//            stu2.setStudentPhone(stu.getStudentPhone());
//            stu2.setStudentAddress(stu.getStudentAddress());
//            stu2.setStudentCB(stu.getStudentCB());
        });
//        Log.d("Tag",stu.getStudentID());
        cancelBt = view.findViewById(R.id.edit_students_cancel);
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        deleteBt = view.findViewById(R.id.edit_students_delete);
        deleteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("Tag",stu2.getStudentID());
                Model.getInstance().deleteStudent(stu, new Model.deleteStudentListener() {
                    @Override
                    public void onComplete() {
                        Navigation.findNavController(view).navigate(R.id.studentListFragment);
                    }
                });
            }
        });

        saveBt = view.findViewById(R.id.edit_students_save);
        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
//                Student s= new Student();
//                s.setStudentName(name.getText().toString());
//                s.setStudentID(id.getText().toString());
//                s.setStudentPhone(phone.getText().toString());
//                s.setStudentAddress(address.getText().toString());
//                s.setStudentCB(cb.isChecked());
//                Model.getInstance().editStudent(stu, s, ()->{
//                    Navigation.findNavController(view).navigateUp();
//                });
            }
        });
        return view;
    }
    private void save() {
        pb.setVisibility(View.VISIBLE);
        saveBt.setEnabled(false);
        cancelBt.setEnabled(false);
        deleteBt.setEnabled(false);
        Student s= new Student();
        s.setStudentName(name.getText().toString());
        s.setStudentID(id.getText().toString());
        s.setStudentPhone(phone.getText().toString());
        s.setStudentAddress(address.getText().toString());
        s.setStudentCB(cb.isChecked());
        Model.getInstance().editStudent(s, ()->{
            Navigation.findNavController(view).navigateUp();
        });
    }
    private void setData(Student s) {
        name.setText(s.getStudentName());
        id.setText(s.getStudentID());
        phone.setText(s.getStudentPhone());
        address.setText(s.getStudentAddress());
        cb.setChecked(s.getStudentCB());
        pb.setVisibility(View.GONE);
    }

}