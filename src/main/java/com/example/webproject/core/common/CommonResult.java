package com.example.webproject.core.common;





/**
 * 通用返回对象
 */
//@ApiModel("通用返回对象")
public class CommonResult<T> {

    /**
     * 状态码
     */
//    @ApiModelProperty(value = "响应Code： 200-成功，500-失败，401-未登陆，402-参数校验错误，403-无权限", position = 1)
    private Integer code;
    /**
     * 提示信息
     */
//    @ApiModelProperty(value = "响应信息", position = 2)
    private String message;
    /**
     * 数据封装
     */
//    @ApiModelProperty(value = "响应参数", position = 3)
    private T data;


    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    protected CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(200, null, data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<>(200, message, data);
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<>(200,null);
    }

    /**
     * 成功返回消息
     */
    public static <T> CommonResult<T> success(String message) {
        return new CommonResult<>(200,message);
    }
//
//    /**
//     * 失败返回结果
//     *
//     * @param errorCode 错误码
//     */
//    public static <T> CommonResult<T> failed(ResultCode errorCode) {
//        return new CommonResult<>(errorCode.getCode(), errorCode.getMessage());
//    }
//
//    /**
//     * 失败返回结果
//     *
//     * @param errorCode 错误码
//     * @param message   错误信息
//     */
//    public static <T> CommonResult<T> failed(ResultCode errorCode, String message) {
//        return new CommonResult<>(errorCode.getCode(), message);
//    }

    /**
     * 失败返回结果
     *
     */
    public static <T> CommonResult<T> failed(String msg) {
        return new CommonResult<>(500, msg);
    }
    public static <T> CommonResult<T> failed(Integer code ,String msg) {
        return new CommonResult<>(code, msg);
    }

//    /**
//     * 失败返回结果
//     *
//     * @param message 提示信息
//     */
//    public static <T> CommonResult<T> failed(String message, T data) {
//        return new CommonResult<>(ResultCode.FAILED.getCode(), message, data);
//    }
//
//
//    /**
//     * 失败返回结果
//     */
//    public static <T> CommonResult<T> failed() {
//        return failed(ResultCode.FAILED);
//    }
//
//    /**
//     * 服务端异常
//     *
//     */
//    public static <T> CommonResult<T> exception(String msg) {
//        return new CommonResult<>(ResultCode.EXCEPTION.getCode(), msg);
//    }
//
//    /**
//     * 参数验证失败返回结果
//     */
//    public static <T> CommonResult<T> validateFailed() {
//        return failed(ResultCode.VALIDATE_FAILED);
//    }
//
//    /**
//     * 参数验证失败返回结果
//     *
//     * @param message 提示信息
//     */
//    public static <T> CommonResult<T> validateFailed(String message) {
//        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), message);
//    }
//
//    /**
//     * 未登录返回结果
//     */
//    public static <T> CommonResult<T> unAuthenticated(T data) {
//        return new CommonResult<>(ResultCode.UNAUTHENTICATED.getCode(), ResultCode.UNAUTHENTICATED.getMessage(), data);
//    }
//
//    /**
//     * 未授权返回结果
//     */
//    public static <T> CommonResult<T> unAuthorized(T data) {
//        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
//    }
//
//    public static boolean judgeSuccess(CommonResult result) {
//        return result != null && ResultCode.SUCCESS.getCode().equals(result.getCode());
//    }
//
//    /**
//     * 未授权返回结果
//     */
//    public static <T> CommonResult<T> forbidden(T data) {
//        return new CommonResult<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
//    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
