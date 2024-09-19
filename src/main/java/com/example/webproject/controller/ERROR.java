package com.example.webproject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

public class ERROR {
    @Controller
    public static class CustomErrorController implements ErrorController {

        @RequestMapping("/error")
        public String handleError(HttpServletRequest request) {
            // 你可以添加自己的逻辑来处理错误，比如获取错误状态码等
            Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
            // 然后你可以重定向到一个错误页面，或者返回一个包含错误信息的JSON
            return "EEROR"; // 返回到错误页面视图
        }


        public String getErrorPath() {
            return "/error"; // 表示错误处理路径为/error
        }
    }
}
