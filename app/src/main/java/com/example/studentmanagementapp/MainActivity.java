package com.example.studentmanagementapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.studentmanagementapp.adapter.StudentPagerAdapter;
import com.example.studentmanagementapp.database.StudentDatabaseHelper;
import com.example.studentmanagementapp.fragment.StudentListFragment;

public class MainActivity extends AppCompatActivity {
    private EditText etName, etAge, etMajor, etEmail;
    private StudentDatabaseHelper dbHelper;
    private StudentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Database Helper
        dbHelper = new StudentDatabaseHelper(this);

        // Initialize Views
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etMajor = findViewById(R.id.etMajor);
        etEmail = findViewById(R.id.etEmail);

        // Setup ViewPager
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        pagerAdapter = new StudentPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch(position) {
                        case 0: tab.setText("Add Student"); break;
                        case 1: tab.setText("Student List"); break;
                    }
                }).attach();

        // Add Button Listener
        findViewById(R.id.btnAdd).setOnClickListener(v -> addStudent());

        // Delete Button Listener
        findViewById(R.id.btnDelete).setOnClickListener(v -> showDeleteDialog());
    }

    private void addStudent() {
        String name = etName.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();
        String major = etMajor.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        // Validate inputs
        if (name.isEmpty() || ageStr.isEmpty() || major.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            long result = dbHelper.addStudent(name, age, major, email);

            if (result != -1) {
                Toast.makeText(this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
                clearFields();
                // Refresh the student list
                refreshStudentList();
            } else {
                Toast.makeText(this, "Error Adding Student", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid Age", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Student");
        builder.setMessage("Choose deletion method:");

        builder.setPositiveButton("Delete by Name", (dialog, which) -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter student name", Toast.LENGTH_SHORT).show();
                return;
            }

            int deletedCount = dbHelper.deleteStudentByName(name);
            if (deletedCount > 0) {
                Toast.makeText(this, deletedCount + " student(s) deleted", Toast.LENGTH_SHORT).show();
                clearFields();
                refreshStudentList();
            } else {
                Toast.makeText(this, "No students found with that name", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Delete by Email", (dialog, which) -> {
            String email = etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter student email", Toast.LENGTH_SHORT).show();
                return;
            }

            int deletedCount = dbHelper.deleteStudentByEmail(email);
            if (deletedCount > 0) {
                Toast.makeText(this, deletedCount + " student(s) deleted", Toast.LENGTH_SHORT).show();
                clearFields();
                refreshStudentList();
            } else {
                Toast.makeText(this, "No students found with that email", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    private void clearFields() {
        etName.setText("");
        etAge.setText("");
        etMajor.setText("");
        etEmail.setText("");
    }

    // Method to refresh the student list fragment
    private void refreshStudentList() {
        StudentListFragment listFragment = pagerAdapter.getStudentListFragment();
        if (listFragment != null) {
            listFragment.refreshList();
        }
    }
}