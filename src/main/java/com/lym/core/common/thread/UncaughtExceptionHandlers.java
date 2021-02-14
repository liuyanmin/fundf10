package com.lym.core.common.thread;

import lombok.extern.log4j.Log4j;

/**
 * 自定义异常处理
 * Created by liuyanmin on 2020/6/23.
 */
@Log4j
public final class UncaughtExceptionHandlers {

    private UncaughtExceptionHandlers() {
    }

    public static Thread.UncaughtExceptionHandler handle() {
        return new Handle();
    }

    static final class Handle implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            log.error(String.format("Caught an exception in %s.", t), e);
        }
    }
}
