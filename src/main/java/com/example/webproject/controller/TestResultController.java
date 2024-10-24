package com.example.webproject.controller;

import com.example.webproject.core.common.CommonPage;
import com.example.webproject.core.common.CommonResult;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.entity.TestResult;
import com.example.webproject.entity.User;
import com.example.webproject.service.TestResultServer;
import com.example.webproject.service.UploadFileServer;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class TestResultController {
    @Autowired
    private TestResultServer testResultServer;
    @RequestMapping(value="/testResult",method = RequestMethod.POST)
    public CommonResult<CommonPage<TestResult>> TestResultInfo(@RequestBody RowBounds rowBounds)  {
        List<TestResult> testResultList = testResultServer.findInfos(rowBounds);
        return CommonResult.success(CommonPage.restPage(testResultList));
    }


}
