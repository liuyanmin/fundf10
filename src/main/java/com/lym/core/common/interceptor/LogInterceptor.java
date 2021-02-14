package com.lym.core.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.util.IpUtil;
import com.lym.util.ObjectMapperUtil;
import com.lym.util.RequestHolder;
import com.lym.util.io.HttpHelper;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 请求日志拦截器
 * Created by liuyanmin on 2019/9/30.
 */
@Log4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 不拦截OPTIONS请求
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        try {
            // 请求url
            String requestURL = request.getRequestURL().toString();
            // url请求参数
            String queryString = request.getQueryString();

            if (StringUtils.isNotBlank(queryString)) {
                requestURL = requestURL + "?" + queryString;
            }

            // 获取form-data数据
            String key, name;
            Map<String, Object> formDataMap = new HashMap<>();
            Enumeration<String> enumeration = request.getParameterNames();
            if (enumeration != null) {
                while (enumeration.hasMoreElements()) {
                    key = enumeration.nextElement();
                    name = request.getParameter(key);
                    formDataMap.put(key, name);
                }
            }

            // 获取body请求数据
            String body = HttpHelper.getBodyString(request);
            if (StringUtils.isNotBlank(body) && body.startsWith("{") && body.endsWith("}")) {
                JSONObject jsonObject = JSONObject.parseObject(body);
                body = jsonObject.toJSONString();
            }

            String ip = IpUtil.getRequestIp(request);
            String method = request.getMethod();

            Map<String, Object> requestMap = new LinkedHashMap<>();
            requestMap.put("ip", ip);
            requestMap.put("method", method);
            requestMap.put("url", requestURL);
            requestMap.put("body", body);
            requestMap.put("form-data", ObjectMapperUtil.toJsonString(formDataMap));
            log.info("REQUEST : " + objectMapper.writeValueAsString(requestMap));

            // 数据存入ThreadLocal
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("startTime", System.currentTimeMillis());
            RequestHolder.THREAD_LOCAL.set(paramsMap);
        } catch (Exception e) {
            log.error("打印请求日志异常: ", e);
        }
        return true;
    }

}
