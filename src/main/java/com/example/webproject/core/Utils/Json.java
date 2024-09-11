package com.example.webproject.core.Utils;

import com.alibaba.fastjson.JSONObject;

public class Json{
    public JSONObject getObject(String a) {
        JSONObject object= JSONObject.parseObject(a);
        return object;
    }

}
