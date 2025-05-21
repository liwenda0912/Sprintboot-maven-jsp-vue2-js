package com.example.webproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ;
 * @author : lwd
 * @date : 2025-1-24
 */
@Data
public class ZoneCity{
    /**  */
    @TableId(type = IdType.AUTO)
    private Long cityId ;
    /**  */
    private Long provinceId ;
    /**  */
    private Integer state ;
    /**  */
    @JsonProperty("value")
    private String name ;
    /**  */
    private Integer weight ;

    /**  */
}