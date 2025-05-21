package com.example.webproject.config;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class MyMultipartResolver extends CommonsMultipartResolver {

    /**
     * 这里是处理Multipart http的方法。如果这个返回值为true,那么Multipart http body就会MyMultipartResolver 消耗掉.如果这里返回false
     * 那么就会交给后面的自己写的处理函数处理例如刚才ServletFileUpload 所在的函数
     * @see org.springframework.web.multipart.commons.CommonsMultipartResolver#isMultipart(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        // 过滤金格生成文书保存的接口  兼容MultipartResolver 或者 ServletFileUpload
        if (request.getRequestURI().contains("mgt/document/upload")||request.getRequestURI().contains("/modules/document.html")) {
            return false;
        }
        return super.isMultipart(request);
    }

}
