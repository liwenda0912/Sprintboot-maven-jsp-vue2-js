package com.example.webproject.config.mybatisplus;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.webproject.core.enums.dataState;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MetaHandler implements MetaObjectHandler {
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",DateUtil.current()/ 1000, metaObject);
        this.setFieldValByName("state", dataState.NORMAL.getCode(), metaObject);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", DateUtil.current()/ 1000, metaObject);
    }

}
