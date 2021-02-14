package com.lym.core.config.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 日期转换器,将请求参数的日期字符串转换成java.util.Date类型
 * Created by liuyanmin on 2019/10/10.
 */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        return StringToDateUtil.convert(s);
    }
}
