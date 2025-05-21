package com.example.webproject.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.webproject.entity.TestCaseDri;
import com.example.webproject.entity.TestCaseExcel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Mapper
public interface TestCaseDriMapper extends BaseMapper<TestCaseDri> {
    /**
     * 分页查询指定行数据
     *
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    List<TestCaseDri> selectByPage(@Param("ew") Wrapper wrapper);
    Integer insert_(List<TestCaseDri> List);
}
