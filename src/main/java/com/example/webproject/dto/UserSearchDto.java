package com.example.webproject.dto;

import lombok.Data;
@Data
public class UserSearchDto {
        private Integer pageNum;
        private Integer pageSize;
        private Long datetime;
        private String address;
        private String username;
}
