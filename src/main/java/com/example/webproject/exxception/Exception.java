package com.example.webproject.exxception;


import org.springframework.http.HttpStatus;

public class Exception<S> {
    private HttpStatus code;
    private String message;

    public static <T> Exception <String>exception(HttpStatus code , String message) {
        return new Exception<>(code, message);
    }
    public static <T> Exception <String>exception(int code,String message) {
        return new Exception<>(code,message);
    }

    public Exception(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
    public Exception(int code , String message) {
        this.message = message;
        this.code = HttpStatus.valueOf(code);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }
}
