package com.example.webproject.core.Utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class readExcelUtils {
    public JSONArray readSheet(Sheet sheet){
        JSONObject set = null;
        JSONArray array = new JSONArray();
        JsonTransformUtils jsonTransformUilts = new JsonTransformUtils();
        List<String> array_text = new ArrayList<String>();
        // 读取Excel内容的代码
        int sheet_rows =sheet.getLastRowNum();
        for (int j =1 ; j<sheet_rows;j++){
            //获取某行的数据
            Row column = sheet.getRow(j);
            //获取某行的列数
            int columns = column.getLastCellNum();
            for (int i =1;i<columns; i++) {
                // 给数组array_text插入数据
                array_text.add(String.valueOf(column.getCell(i)));
            }
            //将数组和表的第一列数据变成json格式
            JSONObject data = jsonTransformUilts.putJson(array_text, sheet.getRow(0));
            array_text.clear();
            // 将json对象的data添加到json列表
            array.put(data);
        }
        return array;
    }
}
