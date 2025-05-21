package com.example.webproject.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.webproject.core.Utils.roundUpFirstNonZeroDecimal;
import com.example.webproject.core.common.CommonPage;
import com.example.webproject.dto.ZoneCityDto;
import com.example.webproject.dto.ZoneProvinceDto;
import com.example.webproject.entity.User;
import com.example.webproject.entity.ZoneCity;
import com.example.webproject.entity.ZoneProvince;
import com.example.webproject.mapper.ZoneProvinceMapper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneProvinceService {
    @Autowired
    private ZoneProvinceMapper zoneProvinceMapper;

    public List<ZoneProvince> ZoneProvinceQuerys(ZoneProvinceDto zoneProvinceDto) {
        QueryWrapper<ZoneProvince> wrapper = new QueryWrapper<>();
        wrapper.eq("state",1);
        return zoneProvinceMapper.selectByProvincePage(wrapper);
    }
    public List<ZoneProvince> ZoneProvinceQuery(ZoneProvinceDto zoneProvinceDto) {
        QueryWrapper<ZoneProvince> wrapper = new QueryWrapper<>();
        List<ZoneProvince> lists_s = null;
        if(zoneProvinceDto.getPageNum()!=null && zoneProvinceDto.getPageSize()!=null){
            PageMethod.startPage(zoneProvinceDto.getPageNum(), zoneProvinceDto.getPageSize());
        }
        if (zoneProvinceDto.getName() != null && StrUtil.isNotBlank(zoneProvinceDto.getName())) {
            // 获取第一个页的数据
            List<ZoneProvince> lists_ = zoneProvinceMapper.selectByProvincePage(wrapper);
            // 获取总页数方法对象
            roundUpFirstNonZeroDecimal roundUpFirstNonZeroDecimal = new roundUpFirstNonZeroDecimal();
            CommonPage<ZoneProvince> com = CommonPage.restPage(lists_);
            // 获取总页数.等于0不向上取整
            int pageTotal  = roundUpFirstNonZeroDecimal.roundUpFirst((float) com.getTotal() / com.getPageSize());
            // 循环总页数
            // 判断是否存在改城市
            for(int i = zoneProvinceDto.getPageNum();i<=pageTotal+1;i++)
                if (lists_.stream().anyMatch(list_ -> list_.getName().contains(zoneProvinceDto.getName()))) {
                    lists_s = lists_;
                    break;
                } else {
                    // 判断i页数是否大于总页数,是则回显第一页的数据(当数据找不到是,默认回显示第一页)
                    if (i < pageTotal + 1) {
                        PageMethod.startPage(i, 10);
                        lists_ = zoneProvinceMapper.selectByProvincePage(wrapper);
                    } else {
                        PageMethod.startPage(1, 10);
                        lists_s = zoneProvinceMapper.selectByProvincePage(wrapper);
                    }
                }
            return lists_s;
        } else {
            return zoneProvinceMapper.selectByProvincePage(wrapper);
        }
    }
}
