package com.example.webproject.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.webproject.entity.User;
import com.example.webproject.entity.ZoneCity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * ;(zone_city)表数据库访问层
 * @author : lwd
 * @date : 2025-1-24
 */
@Mapper
public interface ZoneCityMapper extends BaseMapper<ZoneCity> {


    List<ZoneCity> selectByPage(@Param("ew") Wrapper wrapper);

}