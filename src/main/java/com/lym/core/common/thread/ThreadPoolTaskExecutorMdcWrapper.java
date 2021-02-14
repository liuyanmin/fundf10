package com.lym.core.common.thread;

import com.lym.core.common.util.ThreadMdcUtil;
import org.apache.log4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by liuyanmin on 2020/7/10.
 */
public class ThreadPoolTaskExecutorMdcWrapper extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {
        super.execute(ThreadMdcUtil.wrap(task, MDC.getContext()));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        super.execute(ThreadMdcUtil.wrap(task, MDC.getContext()), startTimeout);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getContext()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getContext()));
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        return super.submitListenable(ThreadMdcUtil.wrap(task, MDC.getContext()));
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return super.submitListenable(ThreadMdcUtil.wrap(task, MDC.getContext()));
    }

}
