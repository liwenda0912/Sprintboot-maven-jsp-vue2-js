package com.example.webproject;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.webproject.core.Utils.JWTUtils;
import com.example.webproject.core.Utils.TokenHandleUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：
     * true表示继续流程（如调用下一个拦截器或处理器）；
     * false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        将头部信息都转换成map
//        if(!validCookie(request,response)){
//            throw new TokenExpiredException("请重新登录!");
//        }
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // 设置 CORS 头
            response.setStatus(HttpServletResponse.SC_OK);
            return true; // 预检请求处理完毕，不再继续处理
        }
        JSONObject map = new JSONObject();
        TokenHandleUtils tokenHandleUtils = new TokenHandleUtils();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        //判断从前端传来的头部信息中AUTH-TOKEN的值是否与我们后台定义的token值一致
        if (request.getHeader("Cookie") != null) {
            String s = "Cookie";
            String splitString = "cookies=";
            ValidCookie(request, response, tokenHandleUtils, s, splitString);
        } else {
            if (request.getHeader("Token") != null) {
                String s = "";
                String splitString = "token";
                ValidCookie(request, response, tokenHandleUtils, s, splitString);
            } else {
                throw new TokenExpiredException("请登录账号!");//
            }
        }
        return true;
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    /**
     * @param token
     * @return 校验token
     */
    /**
     * @param request
     * @param response
     * @param token    设置最新的token
     */
    public void setCookie(HttpServletRequest request, HttpServletResponse response, String token, String REFRESH_TOKEN) {
        String string = REFRESH_TOKEN + "?" + token;
        // 创建一个Cookie对象
        Cookie cookie = new Cookie("cookies", string);

        // 设置Cookie的有效期，例如1天（以秒为单位）
        cookie.setMaxAge(24 * 60 * 60);

        // 设置Cookie的路径，这样该Cookie只会发送给该路径及其子路径的页面
        cookie.setPath("/");

        // 将Cookie添加到响应中
        response.addCookie(cookie);
    }

    public void ValidCookie(HttpServletRequest request, HttpServletResponse response, TokenHandleUtils tokenHandleUtils, String S, String splitString) {
        //获取token和refresh_token
        String TOKEN = null;
        String REFRESH_TOKEN = null;
        // 判断”cookies=“在数组哪个位置,并获取token和刷新token值
        if (!Objects.equals(splitString, "token")) {
            for (String i : request.getHeader(splitString).split(";")) {
                if (i.contains(S)) {
                    TOKEN = i.split("=")[1].split("[?]")[1];
                    REFRESH_TOKEN = i.split("=")[1].split("[?]")[0];
                }
            }
        }else {
            String[] list = request.getHeader(splitString).split("\\+");
            TOKEN = list[0];
            REFRESH_TOKEN = list[1];
        }

        //校验token是否有效
        Map<String, Object> token_ = tokenHandleUtils.userAging(TOKEN);
        Map<String, Object> refresh_token_ = tokenHandleUtils.userAging(REFRESH_TOKEN);
        if (token_.get("state").toString().equals("false")) {
            if (refresh_token_.get("state").toString().equals("false")) {
                throw new TokenExpiredException("token已经过期，请重新登录");
            } else {
                Map<String, String> payload = new HashMap<>();
                // 是否存在用户信息
                if (refresh_token_.get("id") != null && refresh_token_.get("name") != null) {
                    // 获取用户信息并生成token
                    payload.put("id", refresh_token_.get("id").toString());
                    payload.put("name", refresh_token_.get("name").toString());
                    String token = JWTUtils.getToken(payload);
                    // 设置新的token值并在response里面传到前端
                    if (!Objects.equals(splitString, "token")) {
                        setCookie(request, response, token, REFRESH_TOKEN);
                    }else {
                        response.setHeader("Token", token+"+"+REFRESH_TOKEN);
                    }

                }
            }
        }
    }

}
