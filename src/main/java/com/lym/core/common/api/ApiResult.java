package com.lym.core.common.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * REST API 返回结果
 * Created by liuyanmin on 2019/10/11.
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> implements Serializable {

    @ApiModelProperty(value = "响应码")
    private int code;

    @ApiModelProperty(value = "响应消息")
    private String message;

    @ApiModelProperty(value = "数据")
    private T data;


    public static ApiResult ok() {
        return ok(null);
    }


    public static <T extends Object> ApiResult<T> ok(T data) {
        return result(ApiCode.SUCCESS, null, data);
    }


    public static ApiResult fail(ApiCode apiCode) {
        return fail(apiCode, null);
    }


    public static ApiResult fail(ApiCode apiCode, Object data) {
        return fail(apiCode, null, data);
    }


    public static ApiResult fail(ApiCode apiCode, String message) {
        return result(apiCode, message, null);
    }


    public static ApiResult fail(ApiCode apiCode, String message, Object data) {
        return result(apiCode, message, data);
    }

    public static ApiResult fail(int code, String message) {
        return ApiResult.builder()
                .code(code)
                .message(message)
                .build();
    }


    public static ApiResult result(ApiCode apiCode, String message, Object data) {
        if (StringUtils.isBlank(message)) {
            message = apiCode.getMessage();
        }
        return ApiResult.builder()
                .code(apiCode.getCode())
                .message(message)
                .data(data)
                .build();
    }

    @Override
    public String toString() {
        return "{" +
                "code: " + code +
                ", message: '" + message + '\'' +
                ", data: " + data +
                '}';
    }
}
