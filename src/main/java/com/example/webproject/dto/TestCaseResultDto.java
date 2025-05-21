package com.example.webproject.dto;


import lombok.Data;

@Data
public class TestCaseResultDto {
    private Integer pageNum;
    private Integer pageSize;
    private Long startTime;
    private String testCaseName;
    private int testCaseSuccess;
    private int testCaseFail;
    private Integer type;
}
