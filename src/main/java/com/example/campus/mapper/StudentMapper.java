package com.example.campus.mapper;

import com.example.campus.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM student WHERE username = #{username}")
    Student findByUsername(String username);

    @Insert("INSERT INTO student (username, password, name, class_name) VALUES (#{username}, #{password}, #{name}, #{className})")
    int insertStudent(Student student);
}