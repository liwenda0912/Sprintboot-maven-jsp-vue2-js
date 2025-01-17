package com.example.webproject.controller;

import com.example.webproject.core.common.CommonPage;
import com.example.webproject.core.common.CommonResult;
import com.example.webproject.core.enums.ResultCode;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.entity.TestCase;
import com.example.webproject.service.TestCaseTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestCaseTotalController {
    @Autowired
    private TestCaseTotalService testCaseTotalServer;
    @RequestMapping(value="/testCaseTotal",method = RequestMethod.POST)
    public CommonResult<CommonPage<TestCase>> TestResultInfo(@RequestBody RowBounds rowBounds)  {
        List<TestCase> testCaseList = testCaseTotalServer.findInfos(rowBounds);
        return CommonResult.success(CommonPage.restPage(testCaseList), ResultCode.SUCCESS.getMessage());
    }

}
