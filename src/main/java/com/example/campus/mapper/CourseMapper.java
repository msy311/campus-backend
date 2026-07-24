package com.example.campus.mapper;

import com.example.campus.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("SELECT * FROM course")
    List<Course> findAll();
}