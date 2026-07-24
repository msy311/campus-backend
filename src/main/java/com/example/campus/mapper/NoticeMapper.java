package com.example.campus.mapper;

import com.example.campus.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface NoticeMapper {

    @Select("SELECT * FROM notice ORDER BY create_time DESC")
    List<Notice> findAll();
}