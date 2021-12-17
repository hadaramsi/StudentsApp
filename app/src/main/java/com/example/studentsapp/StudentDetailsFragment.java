package com.example.studentsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentDetailsFragment extends Fragment {

    List<Student> Sdata;
    TextView name;
    TextView id;
    TextView phone;
    TextView address;
    CheckBox cb;
    Student s = null;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_details, container, false);
        Sdata = Model.getInstance().getStudentList();
        name=view.findViewById(R.id.students_details_name);
        id=view.findViewById(R.id.students_details_id);
        phone=view.findViewById(R.id.students_details_phone);
        address=view.findViewById(R.id.students_details_address);
        cb=view.findViewById(R.id.students_details_cb);

        String studentID = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentID();


        if(s== null)
            s = Model.getInstance().getStudentByID(studentID);
        if(s !=null) {
            name.setText("Name: " + s.getStudentName());
            id.setText("ID: " + s.getStudentID());
            phone.setText("Phone: " + s.getStudentPhone());
            address.setText("Address: " + s.getStudentAddress());
            boolean flag = s.getStudentCB();
            Log.d("TAG","cb val: " + flag);
            cb.setChecked(flag);
//            if (cb.isChecked() != flag){
//                Log.d("TAG","value not match");
//            }
        }

        Button editBt = view.findViewById(R.id.students_details_edit);
        editBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDetailsFragmentDirections.ActionStudentDetailsFragmentToEditStudentFragment action = StudentDetailsFragmentDirections.actionStudentDetailsFragmentToEditStudentFragment(s.getStudentID());
                Navigation.findNavController(v).navigate(action);
            }
        }
        );
        Log.d("TAG", "test - cb: " +  cb.isChecked());
        return view;
    }
}