package com.lym.core.common.thread;

import com.lym.core.common.util.ThreadMdcUtil;
import org.apache.log4j.MDC;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by liuyanmin on 2020/7/10.
 */
public class ForkJoinPoolMdcWrapper extends ForkJoinPool {

    public ForkJoinPoolMdcWrapper() {
        super();
    }

    public ForkJoinPoolMdcWrapper(int parallelism) {
        super(parallelism);
    }

    public ForkJoinPoolMdcWrapper(int parallelism, ForkJoinWorkerThreadFactory factory,
                                  Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
        super(parallelism, factory, handler, asyncMode);
    }

    @Override
    public void execute(Runnable task) {
        super.execute(ThreadMdcUtil.wrap(task, MDC.getContext()));
    }

    @Override
    public <T> ForkJoinTask<T> submit(Runnable task, T result) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getContext()), result);
    }

    @Override
    public <T> ForkJoinTask<T> submit(Callable<T> task) {
        return super.submit(ThreadMdcUtil.wrap(task, MDC.getContext()));
    }

}
