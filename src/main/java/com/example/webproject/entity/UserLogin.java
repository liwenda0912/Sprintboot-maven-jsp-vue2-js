package com.example.webproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Accessors(chain = true)
public class UserLogin {
    private String id;
    private String username;
    private String password;
    private String token;
    private String refresh;
    private String code;
}
