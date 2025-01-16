package com.example.webproject.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.webproject.core.Utils.JWTUtils;
import com.example.webproject.core.Utils.Json;
import com.example.webproject.dto.CipherDto;
import com.example.webproject.dto.PassWordDto;
import com.example.webproject.dto.UserDto;
import com.example.webproject.entity.UserLogin;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.page.PageMethod;
import com.example.webproject.core.Utils.GetTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.entity.User;
import com.example.webproject.mapper.UserMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.webproject.dto.RowBounds;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
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
    public List<User> findInfos(RowBounds rowBounds) {
        if (rowBounds.getPageNum() != null) {
            PageMethod.startPage(rowBounds.getPageNum(), rowBounds.getPageSize());
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        return userMapper.findAll(wrapper);
    }

    @Transactional
    public UserLogin login(CipherDto cipherDto) throws Exception {
        String string = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        UserLogin UserLogin = objectMapper.readValue(string, UserLogin.class);
        QueryWrapper<UserLogin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", UserLogin.getUsername())
                .eq("password", UserLogin.getPassword());
        if (userMapper.login(wrapper) != null) {
            return userMapper.login(wrapper);
        }
        throw new RuntimeException("用戶不存在!");
    }

    @Transactional
    public String editUser(CipherDto cipherDto) throws Exception {
        String string = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(string, User.class);
        user.setTime(GetTime.getTime());
        if (user.getId() > 0) {
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.eq("id", user.getId())
                    .set("username", user.getUsername())
                    .set("address", user.getAddress())
                    .set("phone", user.getPhone())
                    .set("city", user.getCity())
                    .set("alias", user.getAlias())
                    .set("province", user.getProvince())
                    .set("zip", user.getZip())
                    .set("Time", GetTime.getTime());
            if (userMapper.EditUser(wrapper) > 0) {
                return encrypt(queryById(user.getId()));
            } else {
                return null;
            }
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
    public int insertUser(UserDto userDto) {
        User user_ = new User();
        if (userDto.getRuleForm().getUsername() != null) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", userDto.getRuleForm().getUsername());
            List<User> user_lot = userMapper.findAll(wrapper);
            if (user_lot.size() == 0) {
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
        DecodedJWT verify = JWTUtils.verify(token);
        Map<String, Object> map = new HashMap<>();
        map.put("name", verify.getClaim("name").asString());
        System.out.print(encrypt(map));
        return encrypt(map).toString();
    }
}
