package com.example.webproject.controller;
import javax.imageio.ImageIO;

import com.example.webproject.core.Utils.Kaptcha;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static com.example.webproject.core.Utils.AESCbc.encrypt;

@Controller
public class KaptChaController {
    @RequestMapping("captcha")
    public void getCaptcha(HttpServletResponse response, HttpServletRequest request) throws Exception {
        // 创建 Kaptcha 实例
        com.google.code.kaptcha.impl.DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        kaptcha.setConfig(config);

        // 生成验证码文本
        String text = kaptcha.createText();
        response.setHeader("Custom-Header", encrypt(text));
        response.setHeader("Access-Control-Expose-Headers", "Custom-Header");
        // 将Cookie添加到响应中
        // 生成验证码图片
        BufferedImage image = kaptcha.createImage(text);

        // 将图片写入响应输出流
        ImageIO.write(image, "png", response.getOutputStream());
    }

}
