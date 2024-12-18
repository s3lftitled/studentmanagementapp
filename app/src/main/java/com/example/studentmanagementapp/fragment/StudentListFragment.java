package com.example.studentmanagementapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.studentmanagementapp.R;
import com.example.studentmanagementapp.adapter.StudentAdapter;
import com.example.studentmanagementapp.database.StudentDatabaseHelper;
import com.example.studentmanagementapp.model.Student;

import java.util.List;

public class StudentListFragment extends Fragment {
    private ListView listViewStudents;
    private TextView tvEmptyList;
    private StudentDatabaseHelper dbHelper;
    private StudentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);

        listViewStudents = view.findViewById(R.id.listViewStudents);
        tvEmptyList = view.findViewById(R.id.tvEmptyList);

        dbHelper = new StudentDatabaseHelper(getContext());
        List<Student> studentList = dbHelper.getAllStudents();

        if (studentList.isEmpty()) {
            tvEmptyList.setVisibility(View.VISIBLE);
            listViewStudents.setVisibility(View.GONE);
        } else {
            tvEmptyList.setVisibility(View.GONE);
            listViewStudents.setVisibility(View.VISIBLE);

            adapter = new StudentAdapter(getContext(), studentList);
            listViewStudents.setAdapter(adapter);
        }

        return view;
    }

    // Method to refresh list when data changes
    public void refreshList() {
        List<Student> studentList = dbHelper.getAllStudents();

        if (studentList.isEmpty()) {
            tvEmptyList.setVisibility(View.VISIBLE);
            listViewStudents.setVisibility(View.GONE);
        } else {
            tvEmptyList.setVisibility(View.GONE);
            listViewStudents.setVisibility(View.VISIBLE);

            adapter = new StudentAdapter(getContext(), studentList);
            listViewStudents.setAdapter(adapter);
        }
    }
}