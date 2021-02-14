package com.lym.core.config.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lym.core.common.thread.ThreadPoolExecutorMdcWrapper;
import com.lym.core.common.thread.UncaughtExceptionHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuyanmin on 2020/7/10.
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * 定时任务线程池
     * @return
     */
    @Bean("schedulingExecutor")
    public ExecutorService schedulingExecutor() {
        return new ThreadPoolExecutorMdcWrapper(10,
                20,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setNameFormat("scheduling-pool-%d")
                .setUncaughtExceptionHandler(UncaughtExceptionHandlers.handle()).build(), new ThreadPoolExecutor.AbortPolicy());
    }
}
