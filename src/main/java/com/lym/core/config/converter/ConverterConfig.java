package com.lym.core.config.converter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 转换器配置
 * Created by liuyanmin on 2019/10/10.
 */
@Configuration
public class ConverterConfig {

    @Bean
    public Converter<String, Date> stringToDateConverter() {
        return new StringToDateConverter();
    }

    @Bean
    public Converter<String, Integer> stringToIntegerConverter() {
        return new StringToIntegerConverter();
    }

    @Bean
    public Converter<String, Double> stringToDoubleConverter() {
        return new StringToDoubleConverter();
    }

    @Bean
    public Converter<String, Long> stringToLongConverter() {
        return new StringToLongConverter();
    }
}
