package com.lym.core.config.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lym.core.common.constant.Fundf10InterceptorConfig;
import com.lym.core.common.interceptor.LogInterceptor;
import com.lym.core.config.properties.Fundf10Properties;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

/**
 * 拦截器注册类
 * Created by liuyanmin on 2019/9/30.
 */
@Log4j
@Configuration
public class IntercpetorConfig implements WebMvcConfigurer {

    @Autowired
    private Fundf10Properties fundf10Properties;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private LogInterceptor logInterceptor;


    @PostConstruct
    public void init() throws JsonProcessingException {
        log.info("Fundf10Properties: " + objectMapper.writeValueAsString(fundf10Properties));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        Fundf10InterceptorConfig interceptorConfig = fundf10Properties.getInterceptorConfig();

        // 日志拦截器
        if (interceptorConfig.getLogConfig().isEnabled()) {
            registry.addInterceptor(logInterceptor)
                    .addPathPatterns(interceptorConfig.getLogConfig().getIncludePath())
                    .excludePathPatterns(interceptorConfig.getLogConfig().getExcludePath());
        }

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置swagger静态资源访问
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
