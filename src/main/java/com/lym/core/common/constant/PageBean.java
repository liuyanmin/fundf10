package com.lym.core.common.constant;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页实体类
 * Created by liuyanmin on 2019/8/26.
 */
@Data
@Accessors(chain = true)
public class PageBean<T> {

    @ApiModelProperty(value = "总页数")
    private int totalPage;

    @ApiModelProperty(value = "总记录数")
    private long totalCount;

    @ApiModelProperty(value = "当前页")
    private int currPage;

    @ApiModelProperty(value = "当前页的数量")
    private int currSize;

    @ApiModelProperty(value = "每页的数量")
    private int pageSize;

    @ApiModelProperty(value = "结果集")
    private List<T> list;

    public PageBean(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        this.totalPage = pageInfo.getPages();
        this.totalCount = pageInfo.getTotal();
        this.currPage = pageInfo.getPageNum();
        this.currSize = pageInfo.getSize();
        this.pageSize = pageInfo.getPageSize();
        this.list = pageInfo.getList();
    }

}
