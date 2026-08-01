package com.example.campus.entity;

import java.time.LocalDateTime;

public class Enrollment {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private LocalDateTime enrollTime;

    public Enrollment() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }
    public LocalDateTime getEnrollTime() { return enrollTime; }
    public void setEnrollTime(LocalDateTime enrollTime) { this.enrollTime = enrollTime; }
}