package com.example.campus.mapper;

import com.example.campus.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user ORDER BY id")
    List<User> findAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);

    @Update("UPDATE user SET role_id = #{roleId} WHERE id = #{id}")
    int updateRole(@Param("id") Integer id, @Param("roleId") Integer roleId);
}