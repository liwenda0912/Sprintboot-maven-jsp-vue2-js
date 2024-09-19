package com.example.webproject.service;

import com.example.webproject.core.Utils.UploadFileUtils;
import com.example.webproject.core.Utils.readExcelUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
@Service
public class UploadFileServer {
    public String UploadFile(HttpServletRequest request) throws IOException, FileUploadException {
        readExcelUtils readExcelUtils = new readExcelUtils();
        UploadFileUtils uploadFileUtils = new UploadFileUtils();
        List<Sheet> sheet= uploadFileUtils.uploadFileUtils(request);
        for (int i= 0;i<sheet.size();i++){
            readExcelUtils.readSheet(sheet.get(i));
        }
        return "导入成功";
    }
}



