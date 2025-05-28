package com.example.webproject.service;
import org.apache.poi.ss.usermodel.Workbook;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webproject.core.Utils.UploadFileUtils;
import com.example.webproject.core.Utils.readExcelUtils;
import com.example.webproject.entity.TestCaseDri;
import com.example.webproject.entity.TestCaseExcel;
import com.example.webproject.mapper.TestCaseDriMapper;
import com.example.webproject.mapper.TestCaseMapper;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UploadFileService extends ServiceImpl<TestCaseMapper, TestCaseExcel> {
    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestCaseDriMapper testCaseDriMapper;

    @Transactional
    public String UploadFile(HttpServletRequest request) throws Exception {
        readExcelUtils readExcelUtils = new readExcelUtils();
        UploadFileUtils uploadFileUtils = new UploadFileUtils();
        Map<String, Object>sheet= uploadFileUtils.uploadFileUtils(request);
        List<Sheet>  sheets = (List<Sheet>) sheet.get("sheetList");
        QueryWrapper<TestCaseDri> wrapper = new QueryWrapper<>();
        wrapper.eq("test_case_dri_name",sheet.get("dirname"));
        List<TestCaseDri> testCaseDris = null;
        if (testCaseDriMapper.selectByPage(wrapper).size()==0){
            TestCaseDri testCaseDri = new TestCaseDri();
            testCaseDri.setTestcasedriname((String) sheet.get("dirname"));
            testCaseDri.setRemark("文件导入");
            testCaseDriMapper.insert(testCaseDri);
            testCaseDris = testCaseDriMapper.selectByPage(wrapper);
        }
        for (Sheet rows : sheets) {
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
                if (((String) object.get(readExcelUtils.getRow().getCell(9).toString())).length()>0){
                    TestCaseExcel.setRemarks((String) object.get(readExcelUtils.getRow().getCell(9).toString()));
                }else{
                    TestCaseExcel.setRemarks("文件导入");
                }
                TestCaseExcel.setTestCaseDriId(testCaseDris.get(0).getTestcasedriid());
                List.add(TestCaseExcel);
            }
            saveBatch(List,List.size()); // 批量插入，每批50条
            long end = System.currentTimeMillis();
            System.out.println("一万条数据总耗时：" + (end-start) + "ms" );
        }
        return "导入成功";
    }
}



