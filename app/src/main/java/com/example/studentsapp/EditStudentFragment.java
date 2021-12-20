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
    Student stu = null;
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
        Model.getInstance().getStudentByID(studentID, (stu1)->{
            setData(stu1);
        });
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
                pb.setVisibility(View.GONE);
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
        });
        return view;
    }

    private void setData(Student s) {
        stu = s;
        if(stu != null) {
            name.setText(s.getStudentName());
            id.setText(s.getStudentID());
            phone.setText(s.getStudentPhone());
            address.setText(s.getStudentAddress());
            cb.setChecked(s.getStudentCB());
            pb.setVisibility(View.GONE);
        }
    }

}