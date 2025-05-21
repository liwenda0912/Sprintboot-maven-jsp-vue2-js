package com.example.webproject.core.Utils;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Json{
    public JSONObject getObject(String a) {
        JSONObject object= JSONObject.parseObject(a);
        return object;
    }
    public String getObjectString(String s) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("testCaseDriName", s);
        jsonObject.put("remark", "");
        // 转换为 JSON 字符串
        String jsonString = jsonObject.toString();
        return jsonString;
    }


}
