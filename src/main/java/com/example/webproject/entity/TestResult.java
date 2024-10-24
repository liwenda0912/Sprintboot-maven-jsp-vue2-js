package com.example.webproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.webproject.core.Utils.GetTime;
import lombok.Data;

import java.util.Date;

@Data
@TableName("test_result")
public class TestResult {
    private String resultName;
    private String resultDetail;
    @TableId(type = IdType.AUTO)
    private int id;
    private String TestPeople;
    private String TestTime;
    private int TestData;
    private int state;
    private String TestResult;
    private int   TestCaseId;
}
