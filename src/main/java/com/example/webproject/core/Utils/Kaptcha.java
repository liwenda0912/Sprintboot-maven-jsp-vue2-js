package com.example.webproject.core.Utils;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Kaptcha {
    public Map<Object, Object> KaptCha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map<Object, Object> map = new HashMap<>();
        Properties props = new Properties();
        props.put("kaptcha.border", "no");
        props.put("kaptcha.textproducer.font.color", "black");
        props.put("kaptcha.textproducer.char.space", "4");
        props.put("kaptcha.image.width", "120");
        props.put("kaptcha.image.height", "40");

        Config config = new Config(props);
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
        String text = kaptcha.createText();
        BufferedImage image = kaptcha.createImage(text);
        // 创建或获取会话，并设置验证码
        HttpSession session = request.getSession(true);
        session.setAttribute("code", text);
        // 保存到文件
        ImageIO.write(image, "png", response.getOutputStream());
        map.put("img",image);
        map.put("code",text);
        return map;
    }


}
