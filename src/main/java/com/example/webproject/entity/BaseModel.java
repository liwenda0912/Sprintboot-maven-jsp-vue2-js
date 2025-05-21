package com.example.webproject.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {
    private transient static final long serialVersionUID = 1L;

    @TableField(value = "state", fill = FieldFill.INSERT)
    private Integer state;

    @TableField(fill = FieldFill.INSERT,value = "create_time")
    private Long createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE,value = "update_time")
    private Long updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
