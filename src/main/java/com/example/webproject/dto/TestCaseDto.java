package com.example.webproject.dto;


import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class TestCaseDto {
    private Integer pageNum;
    private Integer pageSize;
    private Long startTime;
    private String testCaseName;
    private int testCaseSuccess;
    private int testCaseFail;
    private Integer type;
}
