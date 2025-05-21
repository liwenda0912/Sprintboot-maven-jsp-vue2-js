package com.example.webproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.webproject.entity.TestCaseExcel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ;(testcaseexcel)表数据库访问层
 * @author : lwd
 * @date : 2024-11-20
 */
@Mapper
public interface TestCaseMapper extends BaseMapper<TestCaseExcel> {

        /**
         * 分页查询指定行数据
         *
         * @param wrapper 动态查询条件
         * @return 分页对象列表
         */
        List<TestCaseExcel> selectByPage(@Param("ew") Wrapper wrapper);
        Integer insert_(List<TestCaseExcel> List);
}
