package com.example.webproject.dto;

import lombok.Data;

@Data
public class UserRuleForm {

        private String username;
        private String password;
        private String[] type;
        private Integer zip;
        private boolean delivery;
        private String resource;
        private String phone;
        private String region;
        private String desc;

}
