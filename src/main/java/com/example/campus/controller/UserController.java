package com.example.campus.controller;

import com.example.campus.entity.User;
import com.example.campus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 查询所有用户
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    // 根据ID查询用户
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userMapper.findById(id);
    }

    // 分配角色
    @PutMapping("/{id}/role")
    public String assignRole(@PathVariable Integer id, @RequestParam Integer roleId) {
        int result = userMapper.updateRole(id, roleId);
        return result > 0 ? "角色分配成功" : "分配失败";
    }
}