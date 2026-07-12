package com.example.campus.entity;

public class Student {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String className; // 数据库是 class_name，Java用驼峰

    public Student() {}

    // 用 IDEA 快捷键 Alt+Insert (或右键 -> Generate) 自动生成 Getter 和 Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}