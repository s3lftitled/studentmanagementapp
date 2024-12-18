package com.example.studentmanagementapp.model;

public class Student {
    private long id;
    private String name;
    private int age;
    private String major;
    private String email;

    public Student(long id, String name, int age, String major, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.major = major;
        this.email = email;
    }

    // Getters
    public long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMajor() { return major; }
    public String getEmail() { return email; }
}