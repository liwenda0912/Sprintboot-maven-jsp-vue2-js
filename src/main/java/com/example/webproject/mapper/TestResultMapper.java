package com.example.webproject.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webproject.entity.TestResult;
import com.example.webproject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestResultMapper extends BaseMapper<TestResult> {
    List<TestResult> find(@Param("ew") Wrapper wrapper);
    int Edit(@Param("ew") Wrapper wrapper);
}
