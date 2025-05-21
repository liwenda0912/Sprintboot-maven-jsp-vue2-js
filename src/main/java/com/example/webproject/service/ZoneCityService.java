package com.example.webproject.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.example.webproject.core.Utils.roundUpFirstNonZeroDecimal;
import com.example.webproject.core.common.CommonPage;
import com.example.webproject.dto.ZoneCityDto;
import com.example.webproject.entity.User;
import com.example.webproject.entity.ZoneCity;
import com.example.webproject.mapper.ZoneCityMapper;
import com.example.webproject.mapper.ZoneProvinceMapper;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneCityService {
    @Autowired
    private ZoneCityMapper zoneCityMapper;

    private ZoneProvinceMapper zoneProvinceMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param cityId 主键
     * @return 实例对象
     */
    public ZoneCity queryById(Long cityId){
        return zoneCityMapper.selectById(cityId);
    }

    /**
     * 分页查询
     *
     * @param zoneCityDto 筛选条件
     * @param zoneCityDto 当前页码
     * @param zoneCityDto  每页大小
     * @return
     */
    public List<ZoneCity> paginQuery(ZoneCityDto zoneCityDto) {
        List<ZoneCity> lists_s = null;
        if (zoneCityDto.getPageNum() != null) {
            PageMethod.startPage(zoneCityDto.getPageNum(), zoneCityDto.getPageSize());
        }
        QueryWrapper<ZoneCity> wrapper = new QueryWrapper<>();
        if (zoneCityDto.getName() != null && StrUtil.isNotBlank(zoneCityDto.getName())) {
            // 获取第一个页的数据
            List<ZoneCity> lists_ = zoneCityMapper.selectByPage(wrapper);
            // 获取总页数方法对象
            roundUpFirstNonZeroDecimal roundUpFirstNonZeroDecimal = new roundUpFirstNonZeroDecimal();
            CommonPage<ZoneCity> com = CommonPage.restPage(lists_);

            // 获取总页数.等于0不向上取整
            int pageTotal  = roundUpFirstNonZeroDecimal.roundUpFirst((float) com.getTotal() / com.getPageSize());
            // 循环总页数
            for(int i = zoneCityDto.getPageNum();i<=pageTotal+1;i++){
                // 判断是否存在改城市
                if (lists_.stream().anyMatch(list_ -> list_.getName().contains(zoneCityDto.getName()))) {
                    lists_s = lists_;
                    break;
                }else {
                    // 判断i页数是否大于总页数,是则回显第一页的数据(当数据找不到是,默认回显示第一页)
                    if (i<pageTotal+1){
                        PageMethod.startPage(i, 10);
                        lists_ = zoneCityMapper.selectByPage(wrapper);
                    }else {
                        PageMethod.startPage(1, 10);
                        lists_s = zoneCityMapper.selectByPage(wrapper);
                    }
                }
            }
            return lists_s;
        } else {
            return zoneCityMapper.selectByPage(wrapper);
        }

    }
    public List<ZoneCity> paginQuerys(ZoneCityDto zoneCityDto) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("state",1);
        if (zoneCityDto.getProvinceid()!=null){
            wrapper.eq("province_id",zoneCityDto.getProvinceid());
        }
        return zoneCityMapper.selectByPage(wrapper);
    }

        /**
         * 新增数据
         *
         * @param zoneCity 实例对象
         * @return 实例对象
         */
    public ZoneCity insert(ZoneCity zoneCity){
        zoneCityMapper.insert(zoneCity);
        return zoneCity;
    }

    /**
     * 更新数据
     *
     * @param zoneCity 实例对象
     * @return 实例对象
     */
    public ZoneCity update(ZoneCity zoneCity){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<ZoneCity> chainWrapper = new LambdaUpdateChainWrapper<ZoneCity>(zoneCityMapper);
        if(StrUtil.isNotBlank(zoneCity.getName())){
            chainWrapper.eq(ZoneCity::getName, zoneCity.getName());
        }
        //2. 设置主键，并更新
        chainWrapper.set(ZoneCity::getCityId, zoneCity.getCityId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(zoneCity.getCityId());
        }else{
            return zoneCity;
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param cityId 主键
     * @return 是否成功
     */
    public boolean deleteById(Long cityId){
        int total = zoneCityMapper.deleteById(cityId);
        return total > 0;
    }

}
