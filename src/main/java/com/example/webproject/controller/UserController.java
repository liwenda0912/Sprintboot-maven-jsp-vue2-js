package com.example.webproject.controller;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.webproject.core.Utils.JWTUtils;
import com.example.webproject.core.Utils.MapUtils;
import com.example.webproject.core.common.CommonPage;
import com.example.webproject.core.common.CommonResult;
import com.example.webproject.core.enums.ResultCode;
import com.example.webproject.dto.UserDto;
import com.example.webproject.entity.UserLogin;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.entity.User;
import com.example.webproject.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {
    //    @ResponseBody

    @Autowired
    private UserService userService;
    private Logger log;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<User>> User(@RequestBody RowBounds rowBounds) {
        List<User> touserList = userService.findInfos(rowBounds);
        return CommonResult.success(CommonPage.restPage(touserList));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public CommonResult<User> EditUser(@RequestBody User user) {
        User result_code = userService.editUser(user);
        if (result_code != null) {
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
    public CommonResult<Map<String, Object>> login(@RequestBody UserLogin UserLogin) {
        UserLogin userDB = userService.login(UserLogin);
        MapUtils mapUtils = new MapUtils();
        return CommonResult.success(mapUtils.getToken(userDB));
    }

    @RequestMapping(value = "/userAging", method = RequestMethod.POST)
    public CommonResult<Map<String, Object>> test(String token) {
            return CommonResult.success(userService.verify(token));

    }
}
