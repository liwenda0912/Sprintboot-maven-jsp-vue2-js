package com.example.webproject.controller;

import com.example.webproject.core.common.CommonPage;
import com.example.webproject.core.common.CommonResult;
import com.example.webproject.core.enums.ResultCode;
import com.example.webproject.dto.ZoneCityDto;
import com.example.webproject.dto.ZoneProvinceDto;
import com.example.webproject.entity.ZoneCity;
import com.example.webproject.entity.ZoneProvince;
import com.example.webproject.service.ZoneCityService;
import com.example.webproject.service.ZoneProvinceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.webproject.core.Utils.AESCbc.encrypt;

/**
 * ;(zone_city)表控制层
 * @author : http://www.chiner.pro
 * @date : 2025-1-24
 */
@Api(tags = "对象功能接口")
@RestController
@RequestMapping("/Zone")
public class ZoneController {
    @Autowired
    private ZoneCityService zoneCityService;
    @Autowired
    private ZoneProvinceService zoneProvinceService;


//    /**
//     * 分页查询
//     *
//     * @param zoneCity  筛选条件
//     * @param rowBounds 分页对象
//     * @return 查询结果
//     */
    @RequestMapping(value = "/cityList", method = RequestMethod.POST)
    public  CommonResult<String> paginQuery(@RequestBody ZoneCityDto zoneCityDto) throws Exception {
        List<ZoneCity> zoneCities = zoneCityService.paginQuery(zoneCityDto);
        return CommonResult.success(encrypt(CommonPage.restPage(zoneCities)), ResultCode.SUCCESS.getMessage());
    }
    @RequestMapping(value = "/ProvinceList", method = RequestMethod.POST)
    public  CommonResult<String> Query(@RequestBody ZoneProvinceDto zoneProvinceDto) throws Exception {
        List<ZoneProvince> zoneProvince = zoneProvinceService.ZoneProvinceQuery(zoneProvinceDto);
        return CommonResult.success(encrypt(CommonPage.restPage(zoneProvince)), ResultCode.SUCCESS.getMessage());
    }
    @RequestMapping(value = "/cityList1", method = RequestMethod.POST)
    public  CommonResult<String> paginQuerys(@RequestBody ZoneCityDto zoneCityDto) throws Exception {
        List<ZoneCity> zoneCities = zoneCityService.paginQuerys(zoneCityDto);
        System.out.print(zoneCities);
        return CommonResult.success(encrypt(zoneCities), ResultCode.SUCCESS.getMessage());
    }
    @RequestMapping(value = "/ProvinceList1", method = RequestMethod.POST)
    public  CommonResult<String> Query1(@RequestBody ZoneProvinceDto zoneProvinceDto) throws Exception {
        List<ZoneProvince> zoneProvince = zoneProvinceService.ZoneProvinceQuerys(zoneProvinceDto);
        return CommonResult.success(encrypt(zoneProvince), ResultCode.SUCCESS.getMessage());
    }

}
