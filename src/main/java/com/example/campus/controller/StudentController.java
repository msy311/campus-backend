package com.example.campus.controller;

import com.example.campus.entity.Student;
import com.example.campus.mapper.StudentMapper;
import com.example.campus.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
            // 登录成功，生成JWT令牌
            String token = JwtUtil.generateToken(exist.getUsername());
            return "登录成功，Token: " + token;
        } else {
            return "密码错误";
        }
    }

    // 查询所有学生
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentMapper.findAll();
    }
}