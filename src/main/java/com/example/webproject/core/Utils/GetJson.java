package com.example.webproject.core.Utils;

import com.alibaba.fastjson.JSONObject;

public class GetJson {
    public JSONObject getObject(String a) {
        JSONObject object= JSONObject.parseObject(a);
        return object;
    }
    public JSONObject getObjectString(String s) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("testCaseDriName", s);
        jsonObject.put("remark", "文件导入");
//        // 转换为 JSON 字符串
//        String jsonString = jsonObject.toString();
        return jsonObject;
    }


}
