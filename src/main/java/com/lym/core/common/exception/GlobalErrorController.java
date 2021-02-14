package com.lym.core.common.exception;

import com.lym.core.common.api.ApiCode;
import com.lym.core.common.api.ApiResult;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by liuyanmin on 2019/10/11.
 */
@ApiIgnore
@RestController
@Log4j
public class GlobalErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public ApiResult handlerError() {
        log.error("404 NOT FOUND");
        return ApiResult.fail(ApiCode.NOT_FOUND);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
