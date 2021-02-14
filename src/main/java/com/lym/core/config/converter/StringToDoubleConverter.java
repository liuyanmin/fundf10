package com.lym.core.config.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * Created by liuyanmin on 2019/10/10.
 */
public class StringToDoubleConverter implements Converter<String, Double> {

    @Override
    public Double convert(String s) {
        return StringToDoubleUtil.convert(s);
    }
}
