package com.lym.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liuyanmin on 2019/10/22.
 */
@Data
@Component
@ConfigurationProperties(prefix = "pagehelper")
public class PageProperties {

    private Integer defaultPageNum;

    private Integer defaultPageSize;

}
