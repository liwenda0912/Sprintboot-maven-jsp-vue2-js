package com.example.webproject.dto;

import lombok.Data;

@Data
public class CipherDto {
    private String ciphertext;
    private String iv;
    private String key;
}
