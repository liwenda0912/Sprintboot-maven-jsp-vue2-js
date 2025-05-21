package com.example.webproject.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@TableName("test_case")
public class TestCase extends BaseModel {
    private String testCaseName;
    private String caseTotal;
    @TableId(type = IdType.AUTO)
    private int id;
    private int TestCaseSuccess;
    private int TestCaseFail;
    private int TestCaseError;

}
