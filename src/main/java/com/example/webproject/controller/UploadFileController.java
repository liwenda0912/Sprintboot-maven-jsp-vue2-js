package com.example.webproject.controller;

import com.example.webproject.core.common.CommonResult;
import com.example.webproject.service.UploadFileService;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
public class UploadFileController {
    @Autowired
    private UploadFileService uploadFileServer;
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    public CommonResult<String> UploadFile(HttpServletRequest request) throws Exception {
        String msg =  uploadFileServer.UploadFile(request);
        if (msg.equals("导入成功")){
            return  CommonResult.success("操作成功");
        }
        else {
            return  CommonResult.success("操作失败！");
        }
    }


}
