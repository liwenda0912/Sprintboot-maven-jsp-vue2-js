package com.example.webproject.service;

import com.example.webproject.core.Utils.UploadFileUtils;
import com.example.webproject.core.Utils.readExcelUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@Service
public class UploadFileServer {
    public String UploadFile(HttpServletRequest request) throws IOException, FileUploadException {
        readExcelUtils readExcelUtils = new readExcelUtils();
        UploadFileUtils uploadFileUtils = new UploadFileUtils();
        List<Sheet> sheet= uploadFileUtils.uploadFileUtils(request);
        for (Sheet rows : sheet) {
//            调用读取excel文档服务
            readExcelUtils.readSheet(rows);
        }
        return "导入成功";
    }
}



