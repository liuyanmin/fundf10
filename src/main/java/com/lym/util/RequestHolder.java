package com.lym.util;

import java.util.Map;

/**
 * Created by liuyanmin on 2019/9/30.
 */
public class RequestHolder {

    public static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public static Map<String, Object> get() {
        return THREAD_LOCAL.get();
    }

    public static void set(Map<String, Object> params) {
        THREAD_LOCAL.set(params);
    }

}
