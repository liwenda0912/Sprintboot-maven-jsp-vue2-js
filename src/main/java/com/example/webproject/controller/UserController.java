package com.example.webproject.controller;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.webproject.core.Utils.JWTUtils;
import com.example.webproject.core.common.CommonPage;
import com.example.webproject.core.common.CommonResult;
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

    @RequestMapping(value="/list",method = RequestMethod.POST)
    public CommonResult<CommonPage<User>> User(@RequestBody RowBounds rowBounds)  {
        List<User> touserList = userService.findInfos(rowBounds);
        return CommonResult.success(CommonPage.restPage(touserList));
    }
    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public CommonResult<User> EditUser(@RequestBody User user)  {
        User result_code = userService.editUser(user);
        if (result_code!=null){
            return CommonResult.success(result_code,"操作成功");
        }
        else {
            return CommonResult.failed("操作失败");
        }
    }
    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public CommonResult<Integer> insertUser(@RequestBody UserDto userDto)  {
        int result_code = userService.insertUser(userDto);
        if (result_code>0){
            return CommonResult.success("操作成功");
        }
        else if (result_code==0) {
            return CommonResult.failed(501,"操作失败");
        }else {
            return CommonResult.failed(409,"用户已存在！");
        }
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
        public CommonResult<Map> login(@RequestBody UserLogin UserLogin)  {
        Map<String,Object> map = new HashMap<>();
        try {
            Map<String, String> payload = new HashMap<>();
            String refresh_token = null;
            // 是否重新请求获取最新的token
            if (UserLogin.getToken()!=null && ! UserLogin.getToken().isEmpty()){
                // 获取token的信息
                System.out.print(UserLogin.getToken());
                DecodedJWT verify = JWTUtils.verify(UserLogin.getRefresh());
                // 是否存在用户信息
                if (verify.getClaim("id").asString() !=null && verify.getClaim("name").asString()!=null){
                    payload.put("id", String.valueOf(verify.getClaim("id").asInt()));
                    payload.put("name", verify.getClaim("name").asString());
                    System.out.print(String.valueOf(verify.getClaim("id").asInt()));
                    refresh_token = UserLogin.getRefresh();
                }
                else{
                    throw new Exception("请重新登录！");
                }
            }else {
                //登录
                UserLogin userDB = userService.login(UserLogin);
                payload.put("id",userDB.getId());
                payload.put("name",userDB.getUsername());
                refresh_token = JWTUtils.getRefreshToken(payload);
            }
            // 生成jwt令牌
            String token = JWTUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功！");
            map.put("token",token);  // 响应token\
            map.put("refresh_token",refresh_token);
        } catch (Exception e) {
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return CommonResult.success(map);
    }
    @RequestMapping(value="/userAging",method = RequestMethod.POST)
    public CommonResult<Map> test(String token){
        Map<String,Object> map = new HashMap<>();
        try {
            // 验证令牌
            DecodedJWT verify = JWTUtils.verify(token);
            map.put("state",true);
            map.put("msg","请求成功");
            return CommonResult.success(map);
        } catch (SignatureVerificationException e) {
//            e.printStackTrace();
            map.put("msg","无效签名！");
        }catch (TokenExpiredException e){
//            e.printStackTrace();
            map.put("msg","token过期");
        }catch (AlgorithmMismatchException e){
//            e.printStackTrace();
            map.put("msg","算法不一致");
        }catch (JWTDecodeException e){
//            e.printStackTrace();
            map.put("msg","token无效！");
        }
        map.put("state",false);
        return CommonResult.success(map);
    }
}
