package com.lym.core.config.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * Created by liuyanmin on 2019/10/10.
 */
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String s) {
        return StringToIntegerUtil.convert(s);
    }
}
