package com.example.webproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.webproject.dto.UserRuleForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {
    private String username;
    @JsonIgnore
    private String password;
    @TableId(type = IdType.AUTO)
    private int id;
    private int zip;
    private String city;
    private String province;
    private String address;
    private String phone;
    private String alias;
    private String Time;
//    public String getUsername() {
//        return username;
//    }
//
//    public User(int zip,String username , String passwd, int id, String address, String phone, String alias,String province, String city) {
//        super();
//        this.password=passwd;
//        this.username = username;
//        this.id = id;
//        this.address = address;
//        this.zip = zip;
//        this.phone = phone;
//        this.alias = alias;
//        this.province = province;
//        this.city =city;
//    }
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getPasswd() {
//        return password;
//    }
//
//    public void setPasswd(String passwd) {
//        this.password = passwd;
//    }
//
//    @Override
//    public String toString() {
//        return "USERS [name=" + username + ", passwd=" + password + ",id="+id+",address="+address+",phone="+phone+",alias="+alias+",province="+province+",city="+city+",zip="+zip+"]";
//    }
//
//    public User() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAlias() {
//        return alias;
//    }
//
//    public void setAlias(String alias) {
//        this.alias = alias;
//    }
//
//    public String getProvince() {
//        return province;
//    }
//
//    public void setProvince(String province) {
//        this.province = province;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public int getZip() {
//        return zip;
//    }
//
//    public void setZip(int zip) {
//        this.zip = zip;
//    }
}
//
