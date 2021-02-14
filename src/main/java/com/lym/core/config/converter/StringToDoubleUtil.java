package com.lym.core.config.converter;

import org.apache.commons.lang3.StringUtils;

/**
 * 空字符串("")转换成Double的null
 *
 * Created by liuyanmin on 2019/10/9.
 */
public class StringToDoubleUtil {

    public static Double convert(String source) {
        if (StringUtils.isBlank(source)){
            return null;
        }
        Double d = Double.parseDouble(source);
        return d;
    }

}
