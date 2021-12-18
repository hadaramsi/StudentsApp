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
import android.widget.TextView;

import com.example.studentsapp.R;
import com.example.studentsapp.StudentDetailsFragmentArgs;
import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.LinkedList;
import java.util.List;


public class EditStudentFragment extends Fragment {

    List<Student> SData= new LinkedList<Student>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_student, container, false);

        TextView name = view.findViewById(R.id.edit_students_name);
        TextView id = view.findViewById(R.id.edit_students_id);
        TextView phone = view.findViewById(R.id.edit_students_phone);
        TextView address = view.findViewById(R.id.edit_students_address);
        CheckBox cb = view.findViewById(R.id.edit_students_cb);
        Model.getInstance().getStudentList(new Model.GetAllStudentsListener() {
            @Override
            public void onComplete(List<Student> d) {
                SData = d;
                //adapter.notifyDataSetChanged();
            }
        });

        String studentID = EditStudentFragmentArgs.fromBundle(getArguments()).getStudentID();
        Student s = Model.getInstance().getStudentByID(studentID);

        if(s !=null){
            name.setText(s.getStudentName());
            id.setText(s.getStudentID());
            phone.setText(s.getStudentPhone());
            address.setText(s.getStudentAddress());
            cb.setChecked(s.getStudentCB());
        }

        FragmentManager fm = getActivity().getSupportFragmentManager();
        Button cancelBt = view.findViewById(R.id.edit_students_cancel);
        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigateUp();
            }
        }
        );

        Button deleteBt = view.findViewById(R.id.edit_students_delete);
        deleteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().deleteStudent(s);
                Navigation.findNavController(view).navigate(R.id.studentListFragment);
            }
        }
        );

        Button saveBt = view.findViewById(R.id.edit_students_save);
        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.setStudentName(name.getText().toString());
                s.setStudentID(id.getText().toString());
                s.setStudentPhone(phone.getText().toString());
                s.setStudentAddress(address.getText().toString());
                s.setStudentCB(cb.isChecked());
                Navigation.findNavController(view).navigateUp();
            }
        }
        );
        return view;
    }

}