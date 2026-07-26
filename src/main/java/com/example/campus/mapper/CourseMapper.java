package com.example.campus.mapper;

import com.example.campus.entity.Course;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("SELECT * FROM course")
    List<Course> findAll();

    @Select("SELECT * FROM course WHERE id = #{id}")
    Course findById(Integer id);

    @Insert("INSERT INTO course (name, teacher, classroom, capacity) VALUES (#{name}, #{teacher}, #{classroom}, #{capacity})")
    int insert(Course course);

    @Update("UPDATE course SET name=#{name}, teacher=#{teacher}, classroom=#{classroom}, capacity=#{capacity} WHERE id=#{id}")
    int update(Course course);

    @Delete("DELETE FROM course WHERE id=#{id}")
    int deleteById(Integer id);
}