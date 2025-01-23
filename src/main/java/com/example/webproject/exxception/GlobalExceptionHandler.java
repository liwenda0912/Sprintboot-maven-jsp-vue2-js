package com.example.webproject.exxception;
import com.auth0.jwt.exceptions.*;
//import org.apache.shiro.ShiroException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
//import org.apache.shiro.authz.UnauthenticatedException;
//import org.apache.shiro.authz.UnauthorizedException;
import com.example.webproject.core.Utils.MapUtils;
import com.example.webproject.core.common.CommonResult;
import com.example.webproject.core.enums.ResultCode;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/***
 * #全局异常处理
 * @author liwenda
 */


@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    MapUtils mapUtils = new MapUtils();

    @ExceptionHandler(HttpServerErrorException.GatewayTimeout.class)
    public Exception<String> httpServerErrorException(HttpServerErrorException E) {
        return Exception.exception(200, E.getMessage(),null);
    }

    @ExceptionHandler(HandlerException.class)
    public Exception<String> HandlerException(HandlerException E) {
        return Exception.exception(500, E.getMessage(),null);
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
        return Exception.exception(ResultCode.EXCEPTION.getCode(), ResultCode.EXCEPTION.getMessage(),null);
    }
    @ExceptionHandler(value = TokenExpiredException.class)
    public CommonResult<Map<String, Object>> TokenExpired(TokenExpiredException e) {
       return CommonResult.success(mapUtils.getErrorToken(e.getMessage()));
    }
    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public Exception<String> array(ArrayIndexOutOfBoundsException e){
        return Exception.exception(ResultCode.EXCEPTION.getCode(),"服务端异常",null);
    }
    @ExceptionHandler(value = SignatureVerificationException.class)
    public CommonResult<Map<String, Object>> SignatureVerification(SignatureVerificationException e){
        return CommonResult.success( mapUtils.getErrorToken("无效签名！"));
    }
    @ExceptionHandler(value = AlgorithmMismatchException.class)
    public CommonResult<Map<String, Object>> AlgorithmMismatch(AlgorithmMismatchException e){
        return CommonResult.success( mapUtils.getErrorToken("算法不一致"));
    }
    @ExceptionHandler(value = JWTDecodeException.class)
    public CommonResult<Map<String, Object>> JWTDecode(JWTDecodeException e){
        return CommonResult.success(mapUtils.getErrorToken("token无效！"));
    }
    @ExceptionHandler(value = RuntimeException.class)
    public CommonResult<Map<String, Object>> RuntimeException(RuntimeException e){
        return CommonResult.success( mapUtils.getErrorToken("ssss"));
    }
//    @ExceptionHandler(value = NullPointerException.class)
//    public Exception<String> NullPointer(NullPointerException e){
//        return Exception.exception(400,"缺少请求参数!",null);
//    }
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Exception<String> MissingServletRequestParameter(MissingServletRequestParameterException e){
        return Exception.exception(412,"缺少请求参数！",null);
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Exception<String> IllegalArgument(IllegalArgumentException e){
        return Exception.exception(ResultCode.VALIDATELOSS_EXCEPTION.getCode(),ResultCode.VALIDATELOSS_EXCEPTION.getMessage(),null);
    }
    @ExceptionHandler(value = InvocationTargetException.class)
    public Exception<String>  InvocationTarget(InvocationTargetException e){
        // 获取封装的实际异常
        Throwable targetException = e.getCause();
        if (targetException != null) {
            // 根据实际异常的类型进行处理
            if (targetException instanceof IllegalArgumentException) {
                        return Exception.exception(ResultCode.VALIDATELOSS_EXCEPTION.getCode(),ResultCode.VALIDATELOSS_EXCEPTION.getMessage(),null);

            } else if (targetException instanceof NullPointerException) {
                return Exception.exception(ResultCode.VALIDATELOSS_EXCEPTION.getCode(),targetException.getMessage(),null);
            } else {
                return Exception.exception(ResultCode.VALIDATELOSS_EXCEPTION.getCode(),targetException.getMessage(),null);
            }
        } else {
            return Exception.exception(ResultCode.VALIDATELOSS_EXCEPTION.getCode(),"Unknown error occurred during method invocation",null);
        }
    }


}
