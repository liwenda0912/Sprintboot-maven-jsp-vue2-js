package com.example.webproject.core.Utils;
import com.example.webproject.entity.UserLogin;

import java.util.Map;
import java.util.HashMap;

public class MapUtils {
    Map<String, Object> map = new HashMap<>();


    //获取token错误map
    public Map getErrorToken(String s){
        map.put("msg", s);
        map.put("state", false);
        return map;
    }

    // 获取token成功map
    public  Map getToken(UserLogin userLogin){
        // 生成刷新jwt令牌
        String refresh_token = JWTUtils.getRefreshToken(payload(userLogin));
        // 生成jwt令牌
        String token = JWTUtils.getToken(payload(userLogin));
        if (token==null && refresh_token==null){
            throw new NullPointerException("token生成失败，请重新登录！");
        }else{
            // 生成刷新jwt令牌
            map.put("state", true);
            map.put("msg", "认证成功！");
            map.put("token", token);
            map.put("refresh_token", refresh_token);
            return map;
        }

    }

    // 提供token获取数据的hashmap
    public Map<String, String> payload(UserLogin userLogin){
        Map<String, String> payload = new HashMap<>();
        boolean s = true;
        try {
                payload.put("id", userLogin.getId());
                payload.put("name", userLogin.getUsername());
                return payload;
        }catch (Exception e){
            payload.put("e",e.getMessage());
            return payload;
        }
    }
}
