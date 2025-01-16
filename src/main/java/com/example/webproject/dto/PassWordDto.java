package com.example.webproject.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
@Data
public class PassWordDto {
    private String password;
    @TableId(type = IdType.AUTO)
    private int id;

}
