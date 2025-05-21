package com.example.webproject.controller;
import com.example.webproject.core.common.CommonPage;
import com.example.webproject.core.common.CommonResult;
import com.example.webproject.core.enums.ResultCode;
import com.example.webproject.dto.CipherDto;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.dto.TestCaseDriDto;
import com.example.webproject.dto.TestCaseDto;
import com.example.webproject.entity.TestCaseDri;
import com.example.webproject.entity.TestCaseExcel;
import com.example.webproject.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/testCase")
public class TestCaseExcelController {
    @Autowired
    private TestCaseService testCaseService;

    @RequestMapping(value= "/testCaseList", method = RequestMethod.POST)
    public CommonResult<CommonPage<TestCaseExcel>> TestResultInfo(@RequestBody TestCaseDto testCaseDto){
        List<TestCaseExcel> testCaseList = testCaseService.findInfos(testCaseDto);
        return CommonResult.success(CommonPage.restPage(testCaseList));
    }

    @RequestMapping(value="/DriList", method = RequestMethod.POST)
    public CommonResult<CommonPage<TestCaseDri>> TestCaseDri(@RequestBody RowBounds rowBounds){
        List<TestCaseDri> testCaseDriList = testCaseService.findInfo(rowBounds);
        return CommonResult.success(CommonPage.restPage(testCaseDriList));
    }
    @RequestMapping(value="/DriInsert", method = RequestMethod.POST)
    public CommonResult<Integer> TestCaseDriInsert(@RequestBody CipherDto cipherDto) throws Exception{
        int testCaseDriList = testCaseService.insertDir(cipherDto);
        if (testCaseDriList > 0) {
            return CommonResult.success("操作成功");
        } else if (testCaseDriList == 0) {
            return CommonResult.failed(ResultCode.FAILED.getCode(), "操作失败");
        } else {
            return CommonResult.failed(ResultCode.SUCCESS.getCode(), "文档已存在！");
        }
    }
    @RequestMapping(value="/CaseInsert", method = RequestMethod.POST)
    public CommonResult<Integer> TestCaseInsert(@RequestBody CipherDto cipherDto) throws Exception {
        int testCaseDriList = testCaseService.insertCase(cipherDto);
        if (testCaseDriList > 0) {
            return CommonResult.success("操作成功");
        } else if (testCaseDriList == 0) {
            return CommonResult.failed(ResultCode.FAILED.getCode(), "操作失败");
        } else {
            return CommonResult.failed(ResultCode.SUCCESS.getCode(), "用例已存在！");
        }
    }

    @RequestMapping(value="/DriUpdate", method = RequestMethod.POST)
    public CommonResult<String> TestCaseDriUpdate(@RequestBody CipherDto cipherDto) throws Exception {
        String testCaseDriList = testCaseService.updateTestCaseDri(cipherDto);
        if (testCaseDriList.length()>0) {
            return CommonResult.success(testCaseDriList, "操作成功");
        } else {
            return CommonResult.failed("操作失败");
        }
    }
    @RequestMapping(value="/CaseUpdate", method = RequestMethod.POST)
    public CommonResult<String> TestCaseUpdate(@RequestBody CipherDto cipherDto) throws Exception {
        String testCaseDriList = testCaseService.updateCase(cipherDto);
        if (testCaseDriList.length()>0) {
            return CommonResult.success(testCaseDriList, "操作成功");
        } else {
            return CommonResult.failed("操作失败");
        }
    }
}
