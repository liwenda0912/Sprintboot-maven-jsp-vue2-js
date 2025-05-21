package com.example.webproject.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.webproject.dto.CipherDto;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.dto.TestCaseDto;
import com.example.webproject.entity.TestCaseDri;
import com.example.webproject.entity.TestCaseExcel;
import com.example.webproject.mapper.TestCaseDriMapper;
import com.example.webproject.mapper.TestCaseMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.example.webproject.core.Utils.AESCbc.decrypt;
import static com.example.webproject.core.Utils.AESCbc.encrypt;

@Service
public class TestCaseService {
    @Autowired
    private TestCaseMapper testCaseExcelMapper;
    @Autowired
    private TestCaseDriMapper testCaseDriMapper;

    public TestCaseExcel queryById(Integer id) {
        return (TestCaseExcel) testCaseExcelMapper.selectById(id);
    }
    @Transactional
    public List<TestCaseExcel> findInfos(TestCaseDto testCaseDto) {
        if (testCaseDto.getPageNum() != null) {
            PageMethod.startPage(testCaseDto.getPageNum(), testCaseDto.getPageSize());
        }
        QueryWrapper<TestCaseExcel> wrapper = new QueryWrapper<>();
                   wrapper.eq("state",1)
                           .eq("test_case_dri_id",testCaseDto.getTestCaseDriId());
        if (testCaseDto.getCaseTitle()!=null && testCaseDto.getCaseTitle().length()>0){
            wrapper.eq("case_title",testCaseDto.getCaseTitle());
        }
        if (testCaseDto.getCaseTitle()!=null && testCaseDto.getModule().length()>0){
            wrapper.eq("module",testCaseDto.getModule());
        }
        return testCaseExcelMapper.selectByPage(wrapper);
    }
    @Transactional
    public List<TestCaseDri> findInfo(RowBounds rowBounds) {
        if (rowBounds.getPageNum() != null) {
            PageMethod.startPage(rowBounds.getPageNum(), rowBounds.getPageSize());
        }
        QueryWrapper<TestCaseDri> wrapper = new QueryWrapper<>();
        wrapper.eq("state",1);
        return testCaseDriMapper.selectByPage(wrapper);
    }

    @Transactional
    public int insertDir(CipherDto cipherDto) throws Exception {
        String string = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        TestCaseDri testCaseDri = objectMapper.readValue(string, TestCaseDri.class);
        if (testCaseDri.getTestcasedriname() != null) {
            QueryWrapper<TestCaseDri> wrapper = new QueryWrapper<>();
            wrapper.eq("test_case_dri_name", testCaseDri.getTestcasedriname());
            List<TestCaseDri> TestCaseDri_list = testCaseDriMapper.selectByPage(wrapper);
            if (TestCaseDri_list.size() == 0) {
                return testCaseDriMapper.insert(testCaseDri);
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
        @Transactional
        public int insertCase(CipherDto cipherDto) throws Exception {
        String string = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        TestCaseExcel excel = objectMapper.readValue(string, TestCaseExcel.class);
        if (excel.getCaseTitle() != null) {
            QueryWrapper<TestCaseExcel> wrapper = new QueryWrapper<>();
            wrapper.eq("case_title", excel.getCaseTitle());
            List<TestCaseExcel> TestCase_list = testCaseExcelMapper.selectByPage(wrapper);
            if (TestCase_list.size() == 0) {
                return testCaseExcelMapper.insert(excel);
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    public String updateCase(CipherDto cipherDto) throws Exception{
        String string = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        TestCaseExcel excel = objectMapper.readValue(string, TestCaseExcel.class);
        if (excel.getId() > 0) {
            UpdateWrapper<TestCaseExcel> wrapper = new UpdateWrapper<>();
            wrapper.eq("id", excel.getId())
                    .eq("state",1);
            if (testCaseExcelMapper.update(excel,wrapper) > 0) {
                return encrypt(queryById(excel.getId()));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    public String updateTestCaseDri(CipherDto cipherDto) throws Exception{
        String string = new String(decrypt(cipherDto.getCiphertext(), cipherDto.getKey(), cipherDto.getIv()), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        TestCaseDri testCaseDri = objectMapper.readValue(string,TestCaseDri.class);
        if (testCaseDri.getTestcasedriid() > 0) {
            UpdateWrapper<TestCaseDri> wrapper = new UpdateWrapper<>();
            wrapper.eq("test_case_dri_id", testCaseDri.getTestcasedriid() )
                    .eq("state",1);
            if (testCaseDriMapper.update(testCaseDri,wrapper) > 0) {
                return encrypt(queryById(testCaseDri.getTestcasedriid()));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
