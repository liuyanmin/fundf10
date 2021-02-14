package com.lym.core.common.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.MDC;

/**
 * Created by liuyanmin on 2020/7/10.
 */
public class TraceLogUtil {

    /**
     * 生成 traceId
     * @return
     */
    public static String createTraceId() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    /**
     * 获取 traceId
     * @return
     */
    public static String getTraceId() {
        Object traceId = MDC.get(ThreadMdcUtil.LOG_TRACE_ID);
        if (traceId == null) {
            return null;
        }
        return traceId.toString();
    }
}
