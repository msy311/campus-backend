package com.example.campus.controller;

import com.example.campus.entity.Enrollment;
import com.example.campus.mapper.CourseMapper;
import com.example.campus.mapper.EnrollmentMapper;
import com.example.campus.mapper.StudentMapper;
import com.example.campus.entity.Student;
import com.example.campus.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentMapper enrollmentMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    // 选课
    @PostMapping("/enroll")
    public String enroll(@RequestBody Enrollment enrollment, HttpServletRequest request) {
        // 1. 从请求里拿到当前用户名（拦截器存的）
        String currentUsername = (String) request.getAttribute("currentUsername");

        // 2. 根据用户名查到当前学生
        Student currentStudent = studentMapper.findByUsername(currentUsername);
        if (currentStudent == null) {
            return "选课失败：用户信息异常";
        }

        // 3. 检查这门课是否存在
        Course course = courseMapper.findById(enrollment.getCourseId());
        if (course == null) {
            return "选课失败：课程不存在";
        }

        // 4. 检查课容量是否已满
        if (course.getEnrolled() >= course.getCapacity()) {
            return "选课失败：课程已满";
        }

        // 5. 检查是否已选过这门课
        Enrollment exist = enrollmentMapper.findByStudentAndCourse(currentStudent.getId(), enrollment.getCourseId());
        if (exist != null) {
            return "选课失败：你已经选过这门课了";
        }

        // 6. 执行选课
        enrollment.setStudentId(currentStudent.getId());
        enrollmentMapper.enroll(enrollment);

        return "选课成功";
    }

    // 退课
    @DeleteMapping("/drop/{courseId}")
    public String drop(@PathVariable Integer courseId, HttpServletRequest request) {
        // 1. 从请求里拿到当前用户名
        String currentUsername = (String) request.getAttribute("currentUsername");

        // 2. 根据用户名查到当前学生
        Student currentStudent = studentMapper.findByUsername(currentUsername);
        if (currentStudent == null) {
            return "退课失败：用户信息异常";
        }

        // 3. 检查是否选过这门课
        Enrollment exist = enrollmentMapper.findByStudentAndCourse(currentStudent.getId(), courseId);
        if (exist == null) {
            return "退课失败：你还没选这门课";
        }

        // 4. 执行退课
        enrollmentMapper.drop(currentStudent.getId(), courseId);

        return "退课成功";
    }

    // 查询当前学生的所有选课
    @GetMapping("/my")
    public List<Enrollment> getMyEnrollments(HttpServletRequest request) {
        String currentUsername = (String) request.getAttribute("currentUsername");
        Student currentStudent = studentMapper.findByUsername(currentUsername);
        return enrollmentMapper.findByStudentId(currentStudent.getId());
    }
}