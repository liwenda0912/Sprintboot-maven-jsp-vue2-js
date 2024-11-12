package com.example.webproject.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@TableName("test_case")
public class TestCase {
    private String testCaseName;
    private String caseTotal;
    @TableId(type = IdType.AUTO)
    private int id;
    private int TestCaseSuccess;
    private int TestCaseFail;
    private int state;
    private int TestCaseError;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private int startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private int endTime;
}
