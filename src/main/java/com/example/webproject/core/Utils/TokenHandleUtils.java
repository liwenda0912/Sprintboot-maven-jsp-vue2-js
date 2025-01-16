package com.example.webproject.core.Utils;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

public class TokenHandleUtils {
    public Map userAging(String token){
        Map<String,Object> map = new HashMap<>();
        // 防止全局异常操控token过期导致refresh_token无法进行校验
        try {
            // 验证令牌
            DecodedJWT verify = JWTUtils.verify(token);
            map.put("state",true);
            map.put("id",verify.getClaim("id"));
            map.put("name",verify.getClaim("name"));
            map.put("msg","请求成功");
            return map;
        }catch (TokenExpiredException e){
            map.put("state",false);
            map.put("msg",e);
            return map;
        }

    }
}
