package com.example.campus.mapper;

import com.example.campus.entity.Enrollment;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface EnrollmentMapper {

    // 选课：插入一条选课记录
    @Insert("INSERT INTO enrollment (student_id, course_id) VALUES (#{studentId}, #{courseId})")
    int enroll(Enrollment enrollment);

    // 退课：删除选课记录
    @Delete("DELETE FROM enrollment WHERE student_id = #{studentId} AND course_id = #{courseId}")
    int drop(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    // 查询某个学生的所有选课
    @Select("SELECT * FROM enrollment WHERE student_id = #{studentId}")
    List<Enrollment> findByStudentId(Integer studentId);

    // 检查是否已选过这门课
    @Select("SELECT * FROM enrollment WHERE student_id = #{studentId} AND course_id = #{courseId}")
    Enrollment findByStudentAndCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);
}