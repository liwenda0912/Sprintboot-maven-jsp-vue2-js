package com.example.webproject.core.Utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UploadFileUtils {
    public List<Sheet> uploadFileUtils(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        List<Sheet> sheetlist = new ArrayList<>();
        if (isMultipart) {
            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : multiparts) {
                String filename = new File(item.getName()).getName();
                String[] name = filename.split("\\.");
                // 在控制台输出文件的上传路径
                Workbook workbook;
                if (!item.isFormField()) {
                    InputStream fileContent = item.getInputStream();
                    if (name[name.length-1].equals("xls")) {
                        workbook = new HSSFWorkbook(fileContent);
                    } else if (name[name.length-1].equals("xlsx")) {
                        workbook = new XSSFWorkbook(fileContent);
                    } else {
                        throw new Exception("导入文档不是excel文件");
                    }
                    // 获取excel表的表头
                    Sheet sheet = workbook.getSheetAt(0);
                    // 进行导入数据操作
                    sheetlist.add(sheet);
                    workbook.close();
                }
            }
            return sheetlist;
        } else {
            System.out.println("Sorry, this Servlet only handles file upload request");
            return null;
        }
    }
}
