package com.example.studentsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.LinkedList;
import java.util.List;

public class StudentDetailsFragment extends Fragment {

    TextView name;
    TextView id;
    TextView phone;
    TextView address;
    CheckBox cb;
    Student s = null;
    View view;
    ProgressBar pb;
    String studentID = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_details, container, false);


        name=view.findViewById(R.id.students_details_name);
        id=view.findViewById(R.id.students_details_id);
        phone=view.findViewById(R.id.students_details_phone);
        address=view.findViewById(R.id.students_details_address);
        cb = view.findViewById(R.id.students_details_cb1);
        pb = view.findViewById(R.id.student_details_progressBar);
        pb.setVisibility(View.VISIBLE);

        if(studentID== null)
            studentID = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentID();

        Model.getInstance().getStudentByID(studentID,(s)->{
            updateDisplay(s);
        });

        Button editBt = view.findViewById(R.id.students_details_edit);
        editBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDetailsFragmentDirections.ActionStudentDetailsFragmentToEditStudentFragment action = StudentDetailsFragmentDirections.actionStudentDetailsFragmentToEditStudentFragment(s.getStudentID());
                Navigation.findNavController(v).navigate(action);
            }
        });
        return view;
    }
    private void updateDisplay(Student s){
        this.s = s;
        name.setText("Name: " + s.getStudentName());
        id.setText("ID: " + s.getStudentID());
        phone.setText("Phone: " + s.getStudentPhone());
        address.setText("Address: " + s.getStudentAddress());
        cb.setChecked(s.getStudentCB());
        pb.setVisibility(View.GONE);
    }
}