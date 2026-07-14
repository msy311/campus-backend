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

    // 注册接口
    @PostMapping("/register")
    public String register(@RequestBody Student student) {
        Student exist = studentMapper.findByUsername(student.getUsername());
        if (exist != null) {
            return "注册失败：用户名已存在";
        }
        studentMapper.insertStudent(student);
        return "注册成功";
    }

    // 登录接口
    @PostMapping("/login")
    public String login(@RequestBody Student student) {
        Student exist = studentMapper.findByUsername(student.getUsername());
        if (exist == null) {
            return "用户不存在";
        }
        if (exist.getPassword().equals(student.getPassword())) {
            return "登录成功";
        } else {
            return "密码错误";
        }
    }
}