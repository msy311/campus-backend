package com.example.campus.mapper;

import com.example.campus.entity.Notice;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface NoticeMapper {

    @Select("SELECT * FROM notice ORDER BY create_time DESC")
    List<Notice> findAll();

    @Select("SELECT * FROM notice WHERE id = #{id}")
    Notice findById(Integer id);

    @Insert("INSERT INTO notice (title, content) VALUES (#{title}, #{content})")
    int insert(Notice notice);
}