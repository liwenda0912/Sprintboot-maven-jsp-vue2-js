package com.example.webproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.dto.TestCaseResultDto;
import com.example.webproject.entity.TestCase;
import com.example.webproject.entity.TestResult;
import com.example.webproject.mapper.TestCaseTotalMapper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestCaseTotalService {
    @Autowired
    private TestCaseTotalMapper testCaseTotalMapper;

    public TestResult queryById(Integer id) {
        return testCaseTotalMapper.selectById(id);
    }
    @Transactional
    public List<TestCase> findInfos(TestCaseResultDto testCaseDto) {
        if (testCaseDto.getPageNum() != null) {
            PageMethod.startPage(testCaseDto.getPageNum(), testCaseDto.getPageSize());
        }
        QueryWrapper<TestCase> wrapper = new QueryWrapper<>();
        if (testCaseDto.getType() == 1) {
            if (testCaseDto.getTestCaseFail()>=0){
                wrapper.eq("TestCaseFail",testCaseDto.getTestCaseFail());
            }
            if (testCaseDto.getTestCaseSuccess()>=0){
                wrapper.eq("TestCaseSuccess",testCaseDto.getTestCaseSuccess());
            }
            if (testCaseDto.getStartTime()>0){
                wrapper.eq("startTime",testCaseDto.getStartTime());
            }
            if (testCaseDto.getTestCaseName()!=null && testCaseDto.getTestCaseName().length()>0){
                wrapper.eq("testCaseName",testCaseDto.getTestCaseName());
            }
        }
        wrapper.eq("state",1);
        return testCaseTotalMapper.FindInfo(wrapper);
    }
}
