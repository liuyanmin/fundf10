package com.lym.core.common.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.core.config.properties.Fundf10Properties;
import com.lym.util.HttpServletRequestUtil;
import com.lym.util.RequestHolder;
import com.lym.util.UriMatcherUtil;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 打印响应日志
 *
 * Created by liuyanmin on 2019/9/30.
 */
@Log4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Fundf10Properties fundf10Properties;

    // 是否开启响应日志
    @Value("${fundf10.interceptor-config.log-config.response.enabled}")
    private Boolean responseEnabled;


    @Pointcut("execution(public * com.lym.*..controller.*.*(..))")
    public void webLog() {
    }


    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
        String uri = request.getRequestURI();

        // 判断是否开启日志
        if (!fundf10Properties.getInterceptorConfig().getLogConfig().isEnabled() || !responseEnabled) {
            return;
        }

        String[] includePaths = fundf10Properties.getInterceptorConfig().getLogConfig().getIncludePath();
        String[] excludePaths = fundf10Properties.getInterceptorConfig().getLogConfig().getExcludePath();
        // 判断URI是否匹配
        if (!UriMatcherUtil.matcher(includePaths, uri) || UriMatcherUtil.matcher(excludePaths, uri)) {
            return;
        }

        if (ret == null) {
            return;
        }

        String result = ret.toString();
        if (result.startsWith("{") && result.endsWith("}")) {
            try {
                result = objectMapper.writeValueAsString(ret);
            } catch (JsonProcessingException e) {
                log.error("打印响应日志失败: ", e);
            }
        }

        log.info("RESPONSE : " + result);

        // 更新响应时间
        Map<String, Object> threadLocal = RequestHolder.THREAD_LOCAL.get();
        if (threadLocal != null && threadLocal.get("startTime") != null) {
            Long startTime = (Long) threadLocal.get("startTime");
            Long times = System.currentTimeMillis() - startTime;
            log.info("RESPONSE : " + times + "ms\n");
        }
    }

}
