package com.example.webproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("testcaseexcel")
public class TestCaseExcel extends BaseModel {
        /**  */
        @TableId(type = IdType.AUTO)
        private int id ;
        /**  */
        @JsonProperty("Module")
        private String module ;
        /**  */
        @JsonProperty("Scene")
        private String scene ;
        /**  */
        @JsonProperty("CaseTitle")
        private String caseTitle ;
        /**  */
        @JsonProperty("Priority")
        private String priority ;
        /**  */
        @JsonProperty("OperateStep")
        private String operateStep ;
        /**  */
        @JsonProperty("ExpectedResult")
        private String expectedResult ;
        /**  */
        @JsonProperty("ActualResult")
        private String actualResult ;
        /**  */
        @JsonProperty("Remarks")
        private String remarks ;

        private int testCaseDriId;
        /**  */
//        @JsonIgnore
//        private Integer state ;
        /**  */
//        @JsonProperty("CreateTime")
//        private Long createtime ;
//        /**  */
//        @JsonIgnore
//        private Long updatetime ;
}
