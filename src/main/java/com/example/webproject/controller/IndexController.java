package com.example.webproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    //    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpServletResponse response) {
        return "login";
    }

}
