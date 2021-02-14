package com.lym.core.config.properties;

import com.lym.core.common.constant.Fundf10InterceptorConfig;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Created by liuyanmin on 2019/10/10.
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "fundf10")
public class Fundf10Properties {

    @NestedConfigurationProperty
    private Fundf10InterceptorConfig interceptorConfig;

}
