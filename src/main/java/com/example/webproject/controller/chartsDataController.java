package com.example.webproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.webproject.core.common.CommonResult;
import com.example.webproject.service.CountUserNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("data")
@RestController
public class chartsDataController {
    @Autowired
    private CountUserNewService countUserNewService;

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public CommonResult<JSONObject> count(@RequestBody String s){
        return CommonResult.success(countUserNewService.count());
    }
    @RequestMapping(value = "/countTest", method = RequestMethod.POST)
    public CommonResult<List<JSONObject>> countTest(@RequestBody String s){
        return CommonResult.success(countUserNewService.countTest());
    }

}
