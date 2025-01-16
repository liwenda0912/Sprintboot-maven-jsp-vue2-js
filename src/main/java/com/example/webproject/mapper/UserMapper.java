package com.example.webproject.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webproject.entity.User;
import com.example.webproject.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息DAO
 *
 * @author LIWENDA
 * @date 2024/9/2 19:16
**/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> findAll(@Param("ew") Wrapper wrapper);
    int EditUser(@Param("ew") Wrapper wrapper);
    int EditPassword(@Param("ew") Wrapper wrapper);
    UserLogin login(@Param("ew") Wrapper wrapper);

}
