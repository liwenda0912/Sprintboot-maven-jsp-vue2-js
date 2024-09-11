package com.example.webproject.controller;
import com.example.webproject.core.common.CommonPage;
import com.example.webproject.core.common.CommonResult;
import com.github.pagehelper.PageInfo;
import com.example.webproject.dto.RowBounds;
import com.example.webproject.entity.User;
import com.example.webproject.service.UserService;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    //    @ResponseBody

    @Autowired
    private UserService userService;
    @RequestMapping(value="/list",method = RequestMethod.POST)
    public CommonResult<CommonPage<User>> User(@RequestBody RowBounds rowBounds)  {
        List<User> touserList = userService.findInfos(rowBounds);
        return CommonResult.success(CommonPage.restPage(touserList));
    }
    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public CommonResult<CommonPage<Integer>>  EditUser(@RequestBody User user)  {
        System.out.print(user);
        int result_code = userService.editUser(user);
        if (result_code>0){
            return CommonResult.success("操作成功");
        }
        else {
            return CommonResult.failed("操作成功");
        }
    }

}
