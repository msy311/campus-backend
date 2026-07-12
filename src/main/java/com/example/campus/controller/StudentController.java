package com.example.campus.controller;

import com.example.campus.entity.Student;
import com.example.campus.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping("/register")
    public String register(@RequestBody Student student) {
        Student existingStudent = studentMapper.findByUsername(student.getUsername());
        if (existingStudent != null) {
            return "注册失败：用户名已存在";
        }
        studentMapper.insertStudent(student);
        return "注册成功";
    }
}