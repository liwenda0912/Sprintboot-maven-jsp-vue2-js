package com.example.webproject.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webproject.entity.TestCase;
import com.example.webproject.entity.TestResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestCaseTotalMapper extends BaseMapper<TestResult> {
    List<TestCase> FindInfo(@Param("ew") Wrapper wrapper);
    int EditInfo(@Param("ew") Wrapper wrapper);
}
