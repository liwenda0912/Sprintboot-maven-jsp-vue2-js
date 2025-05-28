package com.example.webproject.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.webproject.core.Utils.JWTUtils;
import com.example.webproject.core.common.globalDate;
import com.example.webproject.dto.*;
import com.example.webproject.entity.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.page.PageMethod;
import com.example.webproject.core.Utils.GetTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.entity.User;
import com.example.webproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.webproject.core.Utils.AESCbc.decrypt;
import static com.example.webproject.core.Utils.AESCbc.encrypt;

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
    public List<User> findInfos(UserSearchDto rowBounds) {
        if (rowBounds.getPageNum() != null) {
            PageMethod.startPage(rowBounds.getPageNum(), rowBounds.getPageSize());
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (rowBounds.getDatetime() != 0 ){
            wrapper.eq("Time",rowBounds.getDatetime());
        }
        if (rowBounds.getAddress().length()>0 && rowBounds.getDatetime()!= null) {
            wrapper.eq("address",rowBounds.getAddress());
        }
        if(rowBounds.getUsername()!=null && rowBounds.getUsername().length()>0){
            wrapper.eq("username",rowBounds.getUsername());
        }
        wrapper.eq("state",1);
        return userMapper.findAll(wrapper);
    }

    @Transactional
    public UserLogin login(CipherDto cipherDto, HttpServletRequest request) throws Exception {
        String string = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        UserLogin UserLogin = objectMapper.readValue(string, UserLogin.class);
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", UserLogin.getUsername())
                .eq("password", UserLogin.getPassword())
                .eq("state",1);
        if (userMapper.login(wrapper) != null) {
                return userMapper.login(wrapper);

        }
        throw new Exception("用戶不存在或用户已被停用!");
    }

    @Transactional
    public String editUser(CipherDto cipherDto) throws Exception {
        String string = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(string, User.class);
        if (user.getId() > 0) {
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.eq("id", user.getId())
                    .eq("state",1);
            System.out.print(userMapper.update(user,wrapper)+"\n");

            return encrypt(queryById(user.getId()));
        } else {
            return null;
        }
    }



    @Transactional
    public int editPassword(CipherDto cipherDto) throws Exception {
        GetTime.getTime();
        String string = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        PassWordDto passWordDto = objectMapper.readValue(string, PassWordDto.class);
        if (passWordDto.getId() > 0) {
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.eq("id", passWordDto.getId())
                    .set("password", passWordDto.getPassword());
            int result = userMapper.EditPassword(wrapper);
            return Math.max(result, 0);
        } else {
            return 0;
        }
    }

    @Transactional
    public int insertUser(CipherDto cipherDto) throws Exception {
        String string = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(string, User.class);
        if (user.getUsername() != null) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", user.getUsername());
            List<User> user_lot = userMapper.findAll(wrapper);
            if (user_lot.size() == 0) {
                return userMapper.insert(user);
            } else {
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

    public String getName(CipherDto cipherDto) throws Exception {
        String token = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        System.out.print(token);
        DecodedJWT verify = JWTUtils.verify(token);
        Map<String, Object> map = new HashMap<>();
        map.put("name", verify.getClaim("name").asString());
        return encrypt(map).toString();
    }
}
