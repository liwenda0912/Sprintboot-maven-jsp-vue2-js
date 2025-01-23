package com.example.webproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.dto.TestResultDto;
import com.example.webproject.entity.TestResult;
import com.example.webproject.mapper.TestResultMapper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultService {
    @Autowired
    private TestResultMapper testResultMapper;

    public TestResult queryById(Integer id) {
        return testResultMapper.selectById(id);
    }

    public List<TestResult> findInfos(TestResultDto rowBounds) {
        if (rowBounds.getPageNum() != null) {
            PageMethod.startPage(rowBounds.getPageNum(), rowBounds.getPageSize());
        }
        QueryWrapper<TestResult> wrapper = new QueryWrapper<>();
        wrapper.eq("state",1)
                .eq("TestCaseId",rowBounds.getTestCaseTotalId());
        return testResultMapper.find(wrapper);
    }

}
