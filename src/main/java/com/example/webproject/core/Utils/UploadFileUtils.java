package com.example.webproject.core.Utils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.example.webproject.core.Utils.AESCbc.encrypt;
@Component
public class UploadFileUtils {

    public Map<String, Object> uploadFileUtils(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        List<Sheet> sheetlist = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024); // 设置内存中存储的大小阈值（1MB）
        factory.setRepository(new File(System.getProperty("java.io.tmpdir"))); // 设置临时文件存储目录
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(10 * 1024 * 1024); // 设置单个文件的最大大小（10MB）
        upload.setSizeMax(50 * 1024 * 1024);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("file");
        if (isMultipart) {
            List<FileItem> multiparts = upload.parseRequest(request);
            for (MultipartFile item : files) {
                String filename = item.getOriginalFilename();
                String[] name = filename.split("\\.");
                // 在控制台输出文件的上传路径
                Workbook workbook;
                if (!item.isEmpty()) {
                    InputStream fileContent = item.getInputStream();
                    if (name[name.length - 1].equals("xls")) {
                        workbook = new HSSFWorkbook(fileContent);
                    } else if (name[name.length - 1].equals("xlsx")) {
                        workbook = new XSSFWorkbook(fileContent);
                    } else {
                        throw new Exception("导入文档不是excel文件");
                    }
                    String token = request.getHeader("Token");
                    // 获取excel表的表头
                    List<String> list = new ArrayList<>(Arrays.asList(name));
                    list.remove(name[name.length - 1]);
                    Sheet sheet = workbook.getSheetAt(0);
                    // 进行导入数据操作
                    sheetlist.add(sheet);
                    map.put("dirname", String.join("", list));
                    map.put("sheetList",sheetlist);
                    workbook.close();
                }
            }
            return map;
        } else {
            System.out.println("Sorry, this Servlet only handles file upload request");
            return null;
        }
    }
//    public void api(String name, String token) throws Exception {
//        try {
//            // 正确格式的URL
//            URL url = new URL("http://127.0.0.1:8090/testCase/DriInsert");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json; utf-8");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setRequestProperty("Token", token);
//            connection.setDoOutput(true);
//
//            Map<Object, Object> map = new HashMap<>();
//            GetJson jsonInputString = new GetJson(); // 假设getObjectString是一个工具方法
//
//            String encryptedString = encrypt(jsonInputString.getObjectString(name)); // 假设encrypt是一个工具方法
//            map.put("ciphertext", encryptedString.split(":")[0]);
//            map.put("key", encryptedString.split(":")[1]);
//            map.put("iv", encryptedString.split(":")[2]);
//
//            // 将Map转换为JSON字符串
//            JSONObject requestJson = new JSONObject();
//            for (Map.Entry<Object, Object> entry : map.entrySet()) {
//                requestJson.put((String) entry.getKey(), entry.getValue());
//            }
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = requestJson.toString().getBytes(StandardCharsets.UTF_8);
//                os.write(input, 0, input.length);
//            }
//
//            int responseCode = connection.getResponseCode();
//            System.out.println("Response Code: " + responseCode);
//
//            // 读取响应（省略）
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
