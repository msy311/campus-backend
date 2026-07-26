package com.example.campus.controller;

import com.example.campus.entity.Score;
import com.example.campus.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreMapper scoreMapper;

    @PostMapping("/save")
    public String saveScore(@RequestBody Score score) {
        int result = scoreMapper.insert(score);
        return result > 0 ? "成绩录入成功" : "录入失败";
    }

    @GetMapping("/student/{studentId}")
    public List<Score> getByStudentId(@PathVariable Integer studentId) {
        return scoreMapper.findByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Score> getByCourseId(@PathVariable Integer courseId) {
        return scoreMapper.findByCourseId(courseId);
    }
}