package com.chenx.dao;

import com.chenx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DemoDao {
    int insertUser(@Param("user") User user);
    List<User> selectUser();
}
