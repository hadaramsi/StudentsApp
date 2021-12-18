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

    public AddStudentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);

        Model.getInstance().getStudentList(new Model.GetAllStudentsListener() {
            @Override
            public void onComplete(List<Student> d) {
                SData = d;
                //adapter.notifyDataSetChanged();
            }
        });

        Button cancelBt = view.findViewById(R.id.new_students_cancel);
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigateUp();
            }
        });

        Button saveBt = view.findViewById(R.id.new_students_save);
        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = view.findViewById(R.id.new_students_name);
                TextView id = view.findViewById(R.id.new_students_id);
                TextView phone = view.findViewById(R.id.new_students_phone);
                TextView address = view.findViewById(R.id.new_students_address);
                CheckBox cb = view.findViewById(R.id.new_students_cb);
                Student s = new Student(name.getText().toString(), id.getText().toString(), phone.getText().toString(), address.getText().toString(), cb.isChecked());
                ProgressBar pb = view.findViewById(R.id.add_student_progressBar);
                //pb.setVisibility(View.VISIBLE);
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                cancelBt.setEnabled(false);
                saveBt.setEnabled(false);
                pb.setVisibility(View.GONE);
                Model.getInstance().addNewStudent(s,()->{
                    Navigation.findNavController(v).navigateUp();
                });
            }
        });

        return view;
    }
}
