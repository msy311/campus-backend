package com.example.campus.controller;

import com.example.campus.entity.Notice;
import com.example.campus.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    @Autowired
    private NoticeMapper noticeMapper;

    // 查询所有公告
    @GetMapping("/all")
    public List<Notice> getAllNotices() {
        return noticeMapper.findAll();
    }
}