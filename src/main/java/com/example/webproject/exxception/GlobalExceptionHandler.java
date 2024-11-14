package com.example.webproject.exxception;
import com.auth0.jwt.exceptions.TokenExpiredException;
//import org.apache.shiro.ShiroException;
//import org.apache.shiro.authz.UnauthenticatedException;
//import org.apache.shiro.authz.UnauthorizedException;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

/***
 * #全局异常处理
 * @author liwenda
 */


@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpServerErrorException.GatewayTimeout.class)
    public Exception<String> httpServerErrorException(HttpServerErrorException E) {
        return Exception.exception(200, E.getMessage());
    }

    @ExceptionHandler(HandlerException.class)
    public Exception<String> HandlerException(HandlerException E) {
        return Exception.exception(500, E.getMessage());
    }

//    @ExceptionHandler(UnauthorizedException.class)
//    public Exception<String> AuthenticationException(AuthenticationException E) {
//        return Exception.exception(200,"用户未被授权");
//    }
//    @ExceptionHandler(UnauthenticatedException.class)
//    public Exception<String> AuthorizationException(UnauthenticatedException E) {
//        return Exception.exception(200,"身份验证失败");
//    }
//    @ExceptionHandler(ShiroException.class)
//    public Exception<String> ShiroException(ShiroException e) {
//        return Exception.exception(501,e.getMessage());
//    }
    @ExceptionHandler(value = java.lang.Exception.class)
    public Exception<String> allException(java.lang.Exception e) {
        return Exception.exception(500, "服务端异常!!!");
    }
    @ExceptionHandler(value = TokenExpiredException.class)
    public Exception<String> handler(TokenExpiredException e) {
        return Exception.exception(200,"token已经过期，请重新登录!");
    }
    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public Exception<String> array(ArrayIndexOutOfBoundsException e){
        return Exception.exception(500,"服务端异常");

    }
}
