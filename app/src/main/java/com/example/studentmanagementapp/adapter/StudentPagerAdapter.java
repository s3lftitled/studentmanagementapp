package com.example.studentmanagementapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.studentmanagementapp.fragment.StudentListFragment;

public class StudentPagerAdapter extends FragmentStateAdapter {

    private final StudentListFragment studentListFragment;

    public StudentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        // Initialize the fragment instances
        studentListFragment = new StudentListFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Fragment(); // Placeholder for Add Student fragment
            case 1:
                return studentListFragment; // Return the pre-initialized StudentListFragment
            default:
                return new Fragment(); // Default fragment
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Two tabs
    }

    // Add a getter method to access the StudentListFragment instance
    public StudentListFragment getStudentListFragment() {
        return studentListFragment;
    }
}
