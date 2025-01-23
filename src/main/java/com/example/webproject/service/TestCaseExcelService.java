package com.example.webproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.entity.TestCase;
import com.example.webproject.entity.TestCaseExcel;
import com.example.webproject.entity.TestResult;
import com.example.webproject.mapper.TestCaseExcelMapper;
import com.example.webproject.mapper.TestCaseTotalMapper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseExcelService{
    @Autowired
    private TestCaseExcelMapper testCaseExcelMapper;
    public TestCaseExcel queryById(Integer id) {
        return (TestCaseExcel) testCaseExcelMapper.selectById(id);
    }

    public List<TestCaseExcel> findInfos(RowBounds rowBounds) {
        if (rowBounds.getPageNum() != null) {
            PageMethod.startPage(rowBounds.getPageNum(), rowBounds.getPageSize());
        }
        QueryWrapper<TestCaseExcel> wrapper = new QueryWrapper<>();
                   wrapper.eq("state",1);
        return testCaseExcelMapper.selectByPage(wrapper);
    }
}
