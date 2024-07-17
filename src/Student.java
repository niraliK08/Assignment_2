package com.example.json;

import java.util.Map;

public class Student {
    private String name;
    private Map<String, Integer> grades;

    public Student(String name, Map<String, Integer> grades) {
        this.name = name;
        this.grades = grades;
    }

    public String getName() {

        return name;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }

