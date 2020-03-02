package com.bgps.labs.models;

public class Student {
    private int id;
    private String name;
    private String secondName;
    private String surname;
    private Integer studyGroupId;

    public Student(int id, String name, String secondName, String surname, Integer studyGroupId) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.studyGroupId = studyGroupId;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getStudyGroupId() {
        return studyGroupId;
    }

    public void setStudyGroupId(Integer studyGroupId) {
        this.studyGroupId = studyGroupId;
    }
}