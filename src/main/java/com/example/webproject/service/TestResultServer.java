package com.example.webproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.webproject.core.Utils.GetTime;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.entity.TestResult;
import com.example.webproject.entity.User;
import com.example.webproject.mapper.TestResultMapper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServer {
    @Autowired
    private TestResultMapper testResultMapper;

    public TestResult queryById(Integer id) {
        return testResultMapper.selectById(id);
    }

    public List<TestResult> findInfos(RowBounds rowBounds) {
        if (rowBounds.getPageNum() != null) {
            PageMethod.startPage(rowBounds.getPageNum(), rowBounds.getPageSize());
        }
        QueryWrapper<TestResult> wrapper = new QueryWrapper<>();
        return testResultMapper.find(wrapper);
    }

}
