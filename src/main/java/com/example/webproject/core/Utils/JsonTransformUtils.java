package com.example.webproject.core.Utils;

import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject;
import java.util.List;

public class JsonTransformUtils {
    public JSONObject putJson(List<String> array_text, Row array_name) {
        JSONObject jsonObj = new JSONObject();
        for (int i = 0 ;i<array_text.size();i++){
            jsonObj.put(String.valueOf(array_name.getCell(i+1)), array_text.get(i));
        }
        return jsonObj;
    }
}
