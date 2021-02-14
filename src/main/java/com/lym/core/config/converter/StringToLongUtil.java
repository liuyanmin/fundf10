package com.lym.core.config.converter;

import org.apache.commons.lang3.StringUtils;

/**
 * 空字符串("")转换成Long的null
 *
 * Created by liuyanmin on 2019/10/10.
 */
public class StringToLongUtil {

    public static Long convert(String source) {
        if (StringUtils.isBlank(source)){
            return null;
        }
        Long l = Long.parseLong(source);
        return l;
    }
}
