package com.example.campus.mapper;

import com.example.campus.entity.Score;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ScoreMapper {

    @Insert("INSERT INTO score (student_id, course_id, score) VALUES (#{studentId}, #{courseId}, #{score})")
    int insert(Score score);

    @Select("SELECT * FROM score WHERE student_id = #{studentId}")
    List<Score> findByStudentId(Integer studentId);

    @Select("SELECT * FROM score WHERE course_id = #{courseId}")
    List<Score> findByCourseId(Integer courseId);
}