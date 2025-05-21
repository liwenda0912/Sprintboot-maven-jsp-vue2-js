package com.example.webproject.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.webproject.entity.ZoneCity;
import com.example.webproject.entity.ZoneProvince;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ;(zone_Province)表数据库访问层
 * @author : lwd
 * @date : 2025-2-06
 */
@Mapper
public interface ZoneProvinceMapper {
    List<ZoneProvince> selectByProvincePage(@Param("ew") Wrapper wrapper);

}
