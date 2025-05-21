package com.example.webproject.dto;
import lombok.Data;

@Data
public class TestCaseDto {
    private Integer pageNum;
    private Integer pageSize;
    private int testCaseDriId;
    private String caseTitle;
    private String module;
}
