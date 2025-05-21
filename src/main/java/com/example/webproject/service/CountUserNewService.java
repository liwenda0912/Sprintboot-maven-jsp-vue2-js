package com.example.webproject.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.core.Utils.GetTime;
import com.example.webproject.entity.TestCaseDri;
import com.example.webproject.entity.TestCaseExcel;
import com.example.webproject.entity.User;
import com.example.webproject.mapper.TestCaseDriMapper;
import com.example.webproject.mapper.TestCaseMapper;
import com.example.webproject.mapper.UserMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.webproject.core.Utils.GetTime.getFormattedDate;

@Service
public class CountUserNewService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TestCaseDriMapper testCaseDriMapper;
    @Autowired
    private TestCaseMapper testCaseMapper;


    @Transactional
    public JSONObject count() {
        JSONObject jsonObject = new JSONObject();
        List<Integer> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.ge("create_time", (GetTime.getStartTime() * 1000 - (86400000 * i)) / 1000)
                    .le("create_time", (GetTime.getEndTime() * 1000 - (86400000 * i)) / 1000);
            list1.add(userMapper.selectCount(wrapper));
            list2.add(getFormattedDate(GetTime.getStartTime() * 1000 - (86400000 * i)));

        }
        jsonObject.put("data", list1);
        jsonObject.put("date", list2);
        return jsonObject;
    }
    @Transactional
    public List<JSONObject> countTest() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        List<JSONObject> list1 = new ArrayList<>();
        //今日创建用户数统计
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("create_time", GetTime.getStartTime())
                .le("create_time", GetTime.getEndTime());
        jsonObject.put("value", userMapper.selectCount(wrapper));
        jsonObject.put("name", "用户数");
        list1.add(jsonObject);
        //今日新建用例文档数统计
        QueryWrapper<TestCaseDri> wrapperTestCaseDri = new QueryWrapper<>();
        wrapperTestCaseDri.ge("create_time",GetTime.getStartTime())
                .le("create_time", GetTime.getEndTime());
        jsonObject1.put("value", testCaseDriMapper.selectCount(wrapperTestCaseDri));
        jsonObject1.put("name", "用例文档");
        list1.add(jsonObject1);
        //今日新建用例数统计
        QueryWrapper<TestCaseExcel> wrappertestCaseExcelMapper = new QueryWrapper<>();
        wrappertestCaseExcelMapper.ge("create_time", GetTime.getStartTime())
                .le("create_time", GetTime.getEndTime());
        jsonObject2.put("value", testCaseMapper.selectCount(wrappertestCaseExcelMapper));
        jsonObject2.put("name", "用例");
        list1.add(jsonObject2);
        return list1;
    }
}
