package com.lym.core.common.api;

/**
 * Created by liuyanmin on 2019/10/11.
 */
public enum ApiCode {

    // 系统错误码
    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    UNAUTHORIZED(401, "非法访问"),
    NOT_PERMISSION(403, "无操作权限"),
    NOT_FOUND(404, "你请求的路径不存在"),
    DATA_NOT_FOUND(405, "你请求的数据不存在"),
    PARAMETER_EXCEPTION(5001, "请求参数校验异常"),
    PARAMETER_TYPE_MISMATCH_EXCEPTION(5002, "请求参数类型不匹配异常"),
    MISSING_PARAMETER_EXCEPTION(5003, "缺少请求参数异常"),
    HTTP_MEDIA_TYPE_EXCEPTION(5004, "HTTP Media 类型异常"),
    METHOD_NOT_SUPPORTED_EXCEPTION(5005, "HTTP Method 异常"),
    PARAMETER_BINDING_EXCEPTION(5006, "参数绑定异常"),
    ONE_PARAMETER_VALID_EXCEPTION(5007, "单个参数校验异常"),
    SYSTEM_EXCEPTION(99999, "系统异常"),

    ;

    private final int code;
    private final String message;

    ApiCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

}
