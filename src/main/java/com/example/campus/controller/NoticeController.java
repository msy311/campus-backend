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

    // 查询单个公告
    @GetMapping("/{id}")
    public Notice getNoticeById(@PathVariable Integer id) {
        return noticeMapper.findById(id);
    }

    // 发布公告
    @PostMapping("/publish")
    public String publishNotice(@RequestBody Notice notice) {
        int result = noticeMapper.insert(notice);
        return result > 0 ? "公告发布成功" : "发布失败";
    }
}