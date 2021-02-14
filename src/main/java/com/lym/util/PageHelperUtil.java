package com.lym.util;

import com.github.pagehelper.PageHelper;
import com.lym.core.config.properties.PageProperties;
import org.springframework.stereotype.Component;

/**
 * 分页工具类
 * Created by liuyanmin on 2019/10/22.
 */
@Component
public class PageHelperUtil {
    private static PageProperties pageProperties;

    public PageHelperUtil(PageProperties pageProperties) {
        PageHelperUtil.pageProperties = pageProperties;
    }

    public static void startPage(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = pageProperties.getDefaultPageNum();
        }
        if (pageSize == null) {
            pageSize = pageProperties.getDefaultPageSize();
        }
        PageHelper.startPage(pageNum, pageSize);
    }
}
