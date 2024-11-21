package com.example.webproject.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.webproject.core.Utils.JWTUtils;
import com.example.webproject.dto.UserDto;
import com.example.webproject.entity.UserLogin;
import com.github.pagehelper.page.PageMethod;
import com.example.webproject.core.Utils.GetTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.entity.User;
import com.example.webproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.webproject.dto.RowBounds;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Transactional
    public User queryById(Integer id) {
        return userMapper.selectById(id);
    }
    @Transactional
    public List<User> findInfos(RowBounds rowBounds) {
        if (rowBounds.getPageNum() != null) {
            PageMethod.startPage(rowBounds.getPageNum(), rowBounds.getPageSize());
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return userMapper.findAll(wrapper);
    }

    @Transactional
    public UserLogin login(UserLogin UserLogin){
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        wrapper.eq("username",UserLogin.getUsername())
                .eq("password",UserLogin.getPassword());
        System.out.print(userMapper.login(wrapper));
        if (userMapper.login(wrapper)!=null){
            return userMapper.login(wrapper);
        }
        throw  new RuntimeException("登录失败!");
    }

    @Transactional
    public User editUser(User user) {
        user.setTime(GetTime.getTime());
        if (user.getId()>0){
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.eq("id",user.getId())
                    .set("username",user.getUsername())
                    .set("address",user.getAddress())
                    .set("phone",user.getPhone())
                    .set("city",user.getCity())
                    .set("alias",user.getAlias())
                    .set("province",user.getProvince())
                    .set("zip",user.getZip())
                    .set("Time", GetTime.getTime());
            if (userMapper.EditUser(wrapper) > 0) {
                return queryById(user.getId());
            } else {
                return null;
            }
        }
        else {
            return null;
        }

    }
    @Transactional
    public int insertUser(UserDto userDto) {
        User user_ = new User();
        if (userDto.getRuleForm().getUsername() != null) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username",userDto.getRuleForm().getUsername());
            List<User> user_lot= userMapper.findAll(wrapper);
            if (user_lot.size() == 0){
                user_.setUsername(userDto.getRuleForm().getUsername());
                user_.setPassword(userDto.getRuleForm().getPassword());
                user_.setAlias(userDto.getAlias());
                user_.setCity(userDto.getCity());
                user_.setPhone(userDto.getPhone());
                user_.setProvince(userDto.getProvince());
                user_.setZip(userDto.getZip());
                user_.setTime(GetTime.getTime());
                user_.setAddress(userDto.getAddress());
                return userMapper.insert(user_);
            }else {
                return -1;
            }
        } else {
            return 0;
        }
    }
    @Transactional
    public Map<String, Object> verify(String token) {
        Map<String, Object> map = new HashMap<>();
        // 验证令牌
        DecodedJWT verify = JWTUtils.verify(token);
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }

}
