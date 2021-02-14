package com.lym.core.common.constant;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by liuyanmin on 2019/10/10.
 */
@Data
@Accessors(chain = true)
public class Fundf10InterceptorConfig implements Serializable {
    private static final long serialVersionUID = -2738042100246082469L;

    /**
     * 日志拦截器配置
     */
    private InterceptorConfig logConfig;


    @Data
    public static class InterceptorConfig {

        /**
         * 是否启用
         */
        private boolean enabled;

        /**
         * 排除路径
         */
        private String[] excludePath;

        /**
         * 包含的路径
         */
        private String[] includePath;

    }

}
