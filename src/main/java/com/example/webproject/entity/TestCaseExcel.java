package com.example.webproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("testcaseexcel")
public class TestCaseExcel {
        /**  */
        @TableId(type = IdType.AUTO)
        private Integer id ;
        /**  */
        @JsonProperty("Module")
        private String Module ;
        /**  */
        @JsonProperty("Scene")
        private String Scene ;
        /**  */
        @JsonProperty("CaseTitle")
        private String CaseTitle ;
        /**  */
        @JsonProperty("Priority")
        private String Priority ;
        /**  */
        @JsonProperty("OperateStep")
        private String OperateStep ;
        /**  */
        @JsonProperty("ExpectedResult")
        private String ExpectedResult ;
        /**  */
        @JsonProperty("ActualResult")
        private String ActualResult ;
        /**  */
        @JsonProperty("Remarks")
        private String Remarks ;
        /**  */
        private Integer state ;
        /**  */
        private Long createtime ;
        /**  */
        private Long updatetime ;
}
