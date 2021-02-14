package com.lym.core.common.util;

import org.apache.log4j.MDC;

import java.util.Hashtable;
import java.util.concurrent.Callable;

/**
 * Created by liuyanmin on 2020/7/10.
 */
public class ThreadMdcUtil {
    public static final String LOG_TRACE_ID = "traceId";

    public static void setTraceIdIfAbsent() {
        if (MDC.get(LOG_TRACE_ID) == null) {
            MDC.put(LOG_TRACE_ID, TraceLogUtil.createTraceId());
        }
    }

    public static void setTraceId() {
        MDC.put(LOG_TRACE_ID, TraceLogUtil.createTraceId());
    }

    public static void setTraceId(String traceId) {
        MDC.put(LOG_TRACE_ID, traceId);
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Hashtable<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                for (String key : context.keySet()) {
                    MDC.put(key, context.get(key));
                }
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Hashtable<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                for (String key : context.keySet()) {
                    MDC.put(key, context.get(key));
                }
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

}
