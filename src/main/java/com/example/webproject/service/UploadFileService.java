package com.example.webproject.service;

import com.alibaba.fastjson.JSONObject;
import com.example.webproject.core.Utils.UploadFileUtils;
import com.example.webproject.core.Utils.readExcelUtils;
import com.example.webproject.entity.TestCaseExcel;
import com.example.webproject.mapper.TestCaseMapper;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadFileService {
    @Autowired
    private TestCaseMapper testCaseExcelMapper;

    public String UploadFile(HttpServletRequest request) throws Exception {
        readExcelUtils readExcelUtils = new readExcelUtils();
        UploadFileUtils uploadFileUtils = new UploadFileUtils();
        List<Sheet> sheet= uploadFileUtils.uploadFileUtils(request);
        for (Sheet rows : sheet) {
//            调用读取excel文档服务
            JSONArray rows_ = readExcelUtils.readSheet(rows);
            long start = System.currentTimeMillis();
            List<TestCaseExcel> List = new ArrayList<>();
            TestCaseExcel TestCaseExcel;
            for(int i = 0 ;i < rows_.length(); i++) {
                JSONObject object= JSONObject.parseObject(rows_.get(i).toString());
                TestCaseExcel = new TestCaseExcel();
                TestCaseExcel.setModule((String) object.get(readExcelUtils.getRow().getCell(1).toString()));
                TestCaseExcel.setScene((String) object.get(readExcelUtils.getRow().getCell(2).toString()));
                TestCaseExcel.setCaseTitle((String) object.get(readExcelUtils.getRow().getCell(3).toString()));
                TestCaseExcel.setPriority((String) object.get(readExcelUtils.getRow().getCell(4).toString()));
                TestCaseExcel.setOperateStep((String) object.get(readExcelUtils.getRow().getCell(5).toString()));
                TestCaseExcel.setExpectedResult((String) object.get(readExcelUtils.getRow().getCell(7).toString()));
                TestCaseExcel.setActualResult((String) object.get(readExcelUtils.getRow().getCell(8).toString()));
                if (((String) object.get(readExcelUtils.getRow().getCell(8).toString())).length()>0){
                    TestCaseExcel.setRemarks((String) object.get(readExcelUtils.getRow().getCell(9).toString()));
                }else{
                    TestCaseExcel.setRemarks(null);
                }
                List.add(TestCaseExcel);
            }
            testCaseExcelMapper.insert_(List);
            long end = System.currentTimeMillis();
            System.out.println("一万条数据总耗时：" + (end-start) + "ms" );
        }
        return "导入成功";
    }
}



