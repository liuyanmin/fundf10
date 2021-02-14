package com.lym.dao;

import com.lym.domain.FundStockRe;
import com.lym.mapper.FundStockReMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by liuyanmin on 2021/2/14.
 */
@Repository
public class FundStockReDao {

    @Autowired
    private FundStockReMapper fundStockReMapper;

    public void insert(FundStockRe fundStockRe) {
        fundStockRe.setAddTime(LocalDateTime.now());
        fundStockRe.setUpdateTime(LocalDateTime.now());
        fundStockReMapper.insertSelective(fundStockRe);
    }
}
