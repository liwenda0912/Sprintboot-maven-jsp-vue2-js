package com.example.webproject.exxception;


import org.springframework.http.HttpStatus;

import java.util.Map;

public class Exception<S> {
    private int code;
//    private HttpStatus code_;

    private String message;

//    public static <T> Exception <String>exception(HttpStatus code , String message) {
//        return new Exception<>(code, message);
//    }
    public static <T> Exception <String>exception(int code,String message) {
        return new Exception<>(code,message);
    }
    public static <T> Exception <Map> exceptions(int code, String message) {
        return new Exception<>(code,message);
    }

    public Exception(int code, String message) {
        this.code = code;
        this.message = message;
    }
//    public Exception(HttpStatus code_ , String message) {
//        this.message = message;
//        this.code_ = code_;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

//    public HttpStatus getCode_() {
//        return code_;
//    }

//    public void setCode_(HttpStatus code_) {
//        this.code_ = code_;
//    }
}
