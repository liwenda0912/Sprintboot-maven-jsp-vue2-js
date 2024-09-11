package com.example.webproject.service;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.page.PageMethod;
import com.example.webproject.core.Utils.GetTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.entity.User;
import com.example.webproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.UpdatableSqlQuery;
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
    public int editUser(User user){
        GetTime getTime =new GetTime();
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
                    .set("date",getTime.getTime())
            ;
            return userMapper.EditUser(wrapper);
        }
        else {
            return 0;
        }
    }
}
