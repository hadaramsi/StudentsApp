package com.example.studentsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentListFragment extends Fragment {
    List<Student> data;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_student_list, container, false);
        data = Model.getInstance().getStudentList();
        RecyclerView list = v.findViewById(R.id.student_list_rv);
        list.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Student s = data.get(position);
                StudentListFragmentDirections.ActionStudentListFragmentToStudentDetailsFragment action = StudentListFragmentDirections.actionStudentListFragmentToStudentDetailsFragment(s.getStudentID());
                Navigation.findNavController(v).navigate(action);
            }
        });
        adapter.setOnCbClickListener(new OnCbClickListener() {
            @Override
            public void OnCbClick(int position) {
                Student student = data.get(position);
                student.setStudentCB(!student.getStudentCB());
            }
        });

        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.student_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_students:
                Navigation.findNavController(v).navigate(StudentListFragmentDirections.actionStudentListFragmentToAddStudentFragment());
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private final OnItemClickListener listener;
        private final OnCbClickListener cbListener;

        TextView nameTv;
        TextView idTv;
        CheckBox cb;
        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener, OnCbClickListener cbListener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.list_row_name);
            idTv = itemView.findViewById(R.id.list_row_id);
            cb = itemView.findViewById(R.id.list_row_cb);
            this.listener = listener;
            this.cbListener = cbListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(listener != null)
                        listener.onItemClick(pos);
                }
            });
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    cbListener.OnCbClick(pos);
                    Log.d("TAG","cb was clicked " + pos);
                }
            });
        }

        public void bind(Student student) {
            nameTv.setText(student.getStudentName());
            idTv.setText(student.getStudentID());
            cb.setChecked(student.getStudentCB());
        }
    }

    interface OnItemClickListener{
        void onItemClick(int position);
    }
    interface OnCbClickListener{
        void OnCbClick(int position);
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        private OnItemClickListener listener;
        private OnCbClickListener cbListener;

        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }
        void setOnCbClickListener(OnCbClickListener cbListener){
            this.cbListener = cbListener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rowView = getLayoutInflater().inflate(R.layout.student_list_row,parent,false);
            MyViewHolder viewHolder = new MyViewHolder(rowView, listener, cbListener);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Student student = data.get(position);
            holder.bind(student);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}


