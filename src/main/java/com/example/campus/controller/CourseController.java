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

    // 1. 查询所有课程
    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseMapper.findAll();
    }

    // 2. 根据ID查询单个课程
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Integer id) {
        return courseMapper.findById(id);
    }

    // 3. 新增课程
    @PostMapping("/add")
    public String addCourse(@RequestBody Course course) {
        int result = courseMapper.insert(course);
        return result > 0 ? "添加成功" : "添加失败";
    }

    // 4. 修改课程
    @PutMapping("/update")
    public String updateCourse(@RequestBody Course course) {
        int result = courseMapper.update(course);
        return result > 0 ? "修改成功" : "修改失败";
    }

    // 5. 删除课程
    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Integer id) {
        int result = courseMapper.deleteById(id);
        return result > 0 ? "删除成功" : "删除失败";
    }
}