package com.example.webproject.controller;

import com.example.webproject.core.Utils.MapUtils;
import com.example.webproject.core.common.CommonPage;
import com.example.webproject.core.common.CommonResult;
import com.example.webproject.core.enums.ResultCode;
import com.example.webproject.dto.PassWordDto;
import com.example.webproject.dto.UserDto;
import com.example.webproject.dto.CipherDto;
import com.example.webproject.entity.UserLogin;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.entity.User;
import com.example.webproject.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.example.webproject.core.Utils.AESCbc.encrypt;

@RestController
@RequestMapping("/User")
public class UserController {
    //    @ResponseBody

    @Autowired
    private UserService userService;
    private Logger log;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<String> User(@RequestBody RowBounds rowBounds) throws Exception {
        List<User> touserList = userService.findInfos(rowBounds);
        return CommonResult.success(encrypt(CommonPage.restPage(touserList)),ResultCode.SUCCESS.getMessage());
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public CommonResult<String> EditUser(@RequestBody CipherDto cipherDto) throws Exception {
        String result_code = userService.editUser(cipherDto);
        if (result_code.length()>0) {
            return CommonResult.success(result_code, "操作成功");
        } else {
            return CommonResult.failed("操作失败");
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public CommonResult<Integer> insertUser(@RequestBody UserDto userDto) {
        int result_code = userService.insertUser(userDto);
        if (result_code > 0) {
            return CommonResult.success(ResultCode.SUCCESS.getCode(), "操作成功");
        } else if (result_code == 0) {
            return CommonResult.failed(ResultCode.FAILED.getCode(), "操作失败");
        } else {
            return CommonResult.failed(ResultCode.SUCCESS.getCode(), "用户已存在！");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult<String> login(@RequestBody CipherDto cipherDto) throws Exception {
        UserLogin userDB = userService.login(cipherDto);
        MapUtils mapUtils = new MapUtils();
        return CommonResult.success(encrypt(mapUtils.getToken(userDB)),null);
    }

    @RequestMapping(value = "/userAging", method = RequestMethod.POST)
    public CommonResult<Map<String, Object>> test(String token) {
            return CommonResult.success(userService.verify(token));

    }
    @RequestMapping(value = "/editPassword", method = RequestMethod.POST)
    public CommonResult<String> password(@RequestBody CipherDto cipherDto) throws Exception {
        if (userService.editPassword(cipherDto)>0){
            return CommonResult.success(ResultCode.SUCCESS.getMessage());
        }else{
            return CommonResult.success(ResultCode.FAILED.getMessage());
        }
    }
    @RequestMapping(value = "/getName", method = RequestMethod.POST)
    public CommonResult<String> username(@RequestBody CipherDto cipherDto) throws Exception {
        String NAME = userService.getName(cipherDto);
        if (NAME!=null){
            return CommonResult.success(NAME,ResultCode.SUCCESS.getMessage());
        }else {
            return CommonResult.failed(ResultCode.FAILED.getMessage());
        }
    }
}
