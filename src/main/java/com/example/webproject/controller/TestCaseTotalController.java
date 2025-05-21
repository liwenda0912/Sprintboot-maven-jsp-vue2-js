package com.example.webproject.controller;

import com.example.webproject.core.common.CommonPage;
import com.example.webproject.core.common.CommonResult;
import com.example.webproject.core.enums.ResultCode;
import com.example.webproject.dto.TestCaseResultDto;
import com.example.webproject.entity.TestCase;
import com.example.webproject.service.TestCaseTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/TestCaseResultTotal")
public class TestCaseTotalController {
    @Autowired
    private TestCaseTotalService testCaseTotalServer;
    @RequestMapping(value="/testCaseTotal",method = RequestMethod.POST)
    public CommonResult<CommonPage<TestCase>> TestResultInfo(@RequestBody TestCaseResultDto testCaseDto)  {
        System.out.print(testCaseDto.getTestCaseSuccess());
        List<TestCase> testCaseList = testCaseTotalServer.findInfos(testCaseDto);
        return CommonResult.success(CommonPage.restPage(testCaseList), ResultCode.SUCCESS.getMessage());
    }
//    @RequestMapping(value="/testCaseTotalSearch",method = RequestMethod.POST)
//    public CommonResult<CommonPage<TestCase>> TestResultTotalSearchInfo(@RequestBody TestCaseDto testCaseDto)  {
//        List<TestCase> testCaseList = testCaseTotalServer.findInfos(testCaseDto);
//        return CommonResult.success(CommonPage.restPage(testCaseList), ResultCode.SUCCESS.getMessage());
//    }

}
