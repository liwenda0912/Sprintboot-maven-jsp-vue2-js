package com.example.webproject.service;
import com.github.pagehelper.page.PageMethod;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.entity.User;
import com.example.webproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.webproject.dto.RowBounds;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public List<User> findInfos(RowBounds rowBounds){
        if (rowBounds.getPageNum()!=null){
            PageMethod.startPage(rowBounds.getPageNum(),rowBounds.getPageSize());
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("id",1);
        return userMapper.findAll(wrapper);
    }
}
