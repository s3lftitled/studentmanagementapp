package com.example.studentmanagementapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.studentmanagementapp.R;
import com.example.studentmanagementapp.model.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private List<Student> students;

    public StudentAdapter(Context context, List<Student> students) {
        super(context, 0, students);
        this.context = context;
        this.students = students;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Student student = students.get(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_student, parent, false);
        }

        // Lookup view for data population
        TextView tvName = convertView.findViewById(R.id.tvStudentName);
        TextView tvDetails = convertView.findViewById(R.id.tvStudentDetails);

        // Populate the data into the template view using the data object
        tvName.setText(student.getName());
        tvDetails.setText(student.getMajor() + " | Age: " + student.getAge() +
                " | " + student.getEmail());

        // Return the completed view to render on screen
        return convertView;
    }
}