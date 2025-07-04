package com.example.webproject.core.Utils;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class readExcelUtils {
    Row row;
    public JSONArray readSheet(Sheet sheet){
        JSONArray array = new JSONArray();
        JsonTransformUtils jsonTransformUilts = new JsonTransformUtils();
        List<String> array_text = new ArrayList<String>();
        // 读取Excel内容的代码
        int sheet_rows =sheet.getPhysicalNumberOfRows();
        for (int j =1 ; j<sheet_rows;j++){
            //获取行某的数据
            Row column = sheet.getRow(j);
            //获取第一行的列数
            int columns = sheet.getRow(0).getLastCellNum();
            if (column!=null){
                for (int i =1;i<columns; i++) {
                    // 给数组array_text插入数据
                    array_text.add(String.valueOf(column.getCell(i)));
                }
                //将数组和表的第一列数据变成json格式
                JSONObject data = jsonTransformUilts.putJson(array_text, sheet.getRow(0));
                row = sheet.getRow(0);
                array_text.clear();
                // 将json对象的data添加到json列表
                array.put(data);
            }
            }
        return array;
    }
    public Row getRow(){
        return row;
    }

}
