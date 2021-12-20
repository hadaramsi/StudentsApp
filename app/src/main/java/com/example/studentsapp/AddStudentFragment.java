package com.example.studentsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.LinkedList;
import java.util.List;

public class AddStudentFragment extends Fragment {
    List<Student> SData = new LinkedList<Student>();
    TextView name;
    TextView id;
    TextView phone;
    TextView address;
    CheckBox cb;
    //Student s = new Student();
    ProgressBar pb;
    Button cancelBt;
    Button saveBt;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_student, container, false);
        name = view.findViewById(R.id.new_students_name);
        id = view.findViewById(R.id.new_students_id);
        phone = view.findViewById(R.id.new_students_phone);
        address = view.findViewById(R.id.new_students_address);
        cb = view.findViewById(R.id.new_students_cb);
        pb = view.findViewById(R.id.add_student_progressBar);

        cancelBt = view.findViewById(R.id.new_students_cancel);
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

        saveBt = view.findViewById(R.id.new_students_save);
        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        return view;
    }
    private void save() {
        pb.setVisibility(View.VISIBLE);
        saveBt.setEnabled(false);
        cancelBt.setEnabled(false);
        Student s = new Student(name.getText().toString(), id.getText().toString(), phone.getText().toString(), address.getText().toString(), cb.isChecked());
        Model.getInstance().addNewStudent(s,()->{
            Navigation.findNavController(view).navigateUp();
        });
    }

}
