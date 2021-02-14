package com.lym.core.config.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * Created by liuyanmin on 2019/10/10.
 */
public class StringToLongConverter implements Converter<String, Long> {

    @Override
    public Long convert(String s) {
        return StringToLongUtil.convert(s);
    }
}
