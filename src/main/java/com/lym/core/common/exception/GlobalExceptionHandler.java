package com.lym.core.common.exception;

import com.lym.core.common.api.ApiCode;
import com.lym.core.common.api.ApiResult;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liuyanmin on 2019/10/13.
 */
@Log4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    /**
     * HTTP 解析请求参数异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error("httpMessageNotReadableException: ", exception);
        return ApiResult.fail(ApiCode.PARAMETER_EXCEPTION);
    }

    /**
     * 请求参数类型不匹配异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.error("methodArgumentTypeMismatchException: ", exception);
        return ApiResult.fail(ApiCode.PARAMETER_TYPE_MISMATCH_EXCEPTION);
    }

    /**
     * 错误的请求参数异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult missingServletRequestParameterException(MissingServletRequestParameterException exception) {
        log.error("missingServletRequestParameterException: ", exception);
        return ApiResult.fail(ApiCode.MISSING_PARAMETER_EXCEPTION);
    }

    /**
     * HTTP Media 异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpMediaTypeException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult httpMediaTypeException(HttpMediaTypeException exception) {
        log.error("httpMediaTypeException:", exception);
        return ApiResult.fail(ApiCode.HTTP_MEDIA_TYPE_EXCEPTION);
    }

    /**
     * HTTP Method 异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error("httpRequestMethodNotSupportedException:", exception);
        return ApiResult.fail(ApiCode.METHOD_NOT_SUPPORTED_EXCEPTION);
    }

    /**
     * 参数绑定异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("methodArgumentNotValidException:", exception);
        List<String> defaultMsg = exception.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return ApiResult.fail(ApiCode.PARAMETER_BINDING_EXCEPTION, defaultMsg.get(0));
    }

    /**
     * 单个参数校验异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult constraintViolationException(ConstraintViolationException exception) {
        log.error("constraintViolationException:", exception);
        String message = exception.getMessage();
        if (StringUtils.isNotBlank(message)) {
            String[] msgs = message.split(":");
            if (msgs != null && msgs.length == 2) {
                message = StringUtils.trim(msgs[1]);
            }
        }
        return ApiResult.fail(ApiCode.ONE_PARAMETER_VALID_EXCEPTION, message);
    }

    /**
     * 默认的异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult exceptionHandler(Exception exception) {
        log.error("exception:", exception);
        return ApiResult.fail(ApiCode.SYSTEM_EXCEPTION);
    }

    /**
     * 默认的异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult businessExceptionHandler(BusinessException exception) {
        log.error("businessException:", exception);
        return ApiResult.fail(exception.getCode(), exception.getMessage());
    }

}
