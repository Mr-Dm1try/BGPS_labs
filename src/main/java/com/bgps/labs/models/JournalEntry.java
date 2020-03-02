package com.bgps.labs.models;

public class JournalEntry {
    private int id;
    private Integer studentId;
    private Integer studyPlanId;
    private Boolean inTime;
    private Integer count;
    private Integer markId;

    public JournalEntry(int id, Integer studentId, Integer studyPlanId, Boolean inTime, Integer count, Integer markId) {
        this.id = id;
        this.studentId = studentId;
        this.studyPlanId = studyPlanId;
        this.inTime = inTime;
        this.count = count;
        this.markId = markId;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudyPlanId() {
        return studyPlanId;
    }

    public void setStudyPlanId(Integer studyPlanId) {
        this.studyPlanId = studyPlanId;
    }

    public Boolean isInTime() {
        return inTime;
    }

    public void setInTime(Boolean inTime) {
        this.inTime = inTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMarkId() {
        return markId;
    }

    public void setMarkId(Integer markId) {
        this.markId = markId;
    }
}
