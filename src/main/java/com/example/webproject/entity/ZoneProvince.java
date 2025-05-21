package com.example.webproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ZoneProvince {
    @TableId(type = IdType.AUTO)
    private int provinceid;
    @JsonProperty("value")
    private String name;
    private int state;
    private int weight;
}
