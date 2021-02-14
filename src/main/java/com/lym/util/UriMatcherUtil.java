package com.lym.util;

import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.List;

/**
 * URI 匹配
 * Created by liuyanmin on 2019/10/10.
 */
public class UriMatcherUtil {
    public static final AntPathMatcher matcher = new AntPathMatcher();

    public static boolean matcher(List<String> patterns, String uri) {
        for (String pattern : patterns) {
            if (matcher.match(pattern, uri)) {
                return true;
            }
        }
        return false;
    }

    public static boolean matcher(String pattern, String uri) {
        if (matcher.match(pattern, uri)) {
            return true;
        }
        return false;
    }

    public static boolean matcher(String[] arr, String uri) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        List<String> patterns = Arrays.asList(arr);
        return matcher(patterns, uri);
    }

}
