package com.example.webproject.controller;
import com.example.webproject.core.common.CommonPage;
import com.example.webproject.core.common.CommonResult;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.entity.TestCaseExcel;
import com.example.webproject.service.TestCaseExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/testCaseExcel")
public class TestCaseExcelController {
    @Autowired
    private TestCaseExcelService testCaseExcelService;

    @RequestMapping(value="/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<TestCaseExcel>> TestResultInfo(@RequestBody RowBounds rowBounds){
        List<TestCaseExcel> testCaseList = testCaseExcelService.findInfos(rowBounds);
        return CommonResult.success(CommonPage.restPage(testCaseList));
    }
}
