package com.example.webproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.entity.TestCase;
import com.example.webproject.entity.TestResult;
import com.example.webproject.mapper.TestCaseTotalMapper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseTotalServer {
    @Autowired
    private TestCaseTotalMapper testCaseTotalMapper;

    public TestResult queryById(Integer id) {
        return testCaseTotalMapper.selectById(id);
    }

    public List<TestCase> findInfos(RowBounds rowBounds) {
        if (rowBounds.getPageNum() != null) {
            PageMethod.startPage(rowBounds.getPageNum(), rowBounds.getPageSize());
        }
        QueryWrapper<TestCase> wrapper = new QueryWrapper<>();
        return testCaseTotalMapper.FindInfo(wrapper);
    }
}
