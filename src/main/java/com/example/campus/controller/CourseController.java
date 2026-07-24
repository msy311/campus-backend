package com.example.campus.controller;

import com.example.campus.entity.Course;
import com.example.campus.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseMapper courseMapper;

    // 查询所有课程
    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseMapper.findAll();
    }
}