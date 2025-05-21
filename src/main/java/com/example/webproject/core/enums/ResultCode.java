package com.example.webproject.core.enums;

public enum ResultCode {

    /**
     * 枚举了一些常用API操作码
     *
        /**
         * 成功
         */
        SUCCESS(200, "操作成功"),
        /**
         * 失败
         */
        FAILED(501, "操作失败"),
        /**
         * 服务端异常
         */
        EXCEPTION(500, "操作异常"),
       /**
       * 参数不完整
       */
        VALIDATELOSS_EXCEPTION(400, "请求参数缺失或格式错误"),
        /**
         * 参数校验失败
         */
        VALIDATE_FAILED(402, "参数检验失败"),
        /**
         * 参数校验失败
         */
        VERIFY_FAILED(405, "验证码校验失败"),
        /**
         * 日志异常
         */
        LOG_EXCEPTION(406, "日志异常"),
        /**
         * 未认证
         */
        UNAUTHENTICATED(401, "认证失败"),
        /**
         * 未授权
         */
        UNAUTHORIZED(402, "功能未授权"),
        /**
         * 无权限
         */
        FORBIDDEN(403, "没有相关权限"),
        NOTOKEN(404,"Token 缺失、无效或过期"),
        /**
         * 无权限
         */
        NOTENANT(408, "没有相关运营商");

        private final Integer code;
        private final String message;

        ResultCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public static ResultCode getState(int value) {
            ResultCode[] var1 = values();
            int var2 = var1.length;
            for(int var3 = 0; var3 < var2; ++var3) {
                ResultCode enu = var1[var3];
                if (enu.code == value) {
                    return enu;
                }
            }
            return null;
        }
    }
