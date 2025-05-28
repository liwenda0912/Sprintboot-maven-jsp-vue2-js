package com.example.webproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
@TableName("test_case_dri")
public class TestCaseDri extends BaseModel {
    @TableId(type = IdType.AUTO)
    @JsonProperty("testCaseDrId")
    @TableField(value = "test_case_dri_id")
    private Integer testcasedriid ;

    @JsonProperty("testCaseDriName")
    @TableField(value = "test_case_dri_name")
    private String testcasedriname;
//    @TableField(fill = FieldFill.INSERT)
//    private Long createTime;
////    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private Long updateTime;
////    @TableField(fill = FieldFill.INSERT)
//    private Integer state;
    private String remark;
}
