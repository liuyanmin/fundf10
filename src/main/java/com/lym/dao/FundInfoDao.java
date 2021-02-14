package com.lym.dao;

import com.lym.domain.FundInfo;
import com.lym.domain.FundInfoExample;
import com.lym.mapper.FundInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by liuyanmin on 2021/2/14.
 */
@Repository
public class FundInfoDao {

    @Autowired
    private FundInfoMapper fundInfoMapper;


    public void insert(FundInfo fundInfo) {
        fundInfo.setAddTime(LocalDateTime.now());
        fundInfo.setUpdateTime(LocalDateTime.now());
        fundInfoMapper.insertSelective(fundInfo);
    }

    public FundInfo getByFundCode(String fundCode) {
        FundInfoExample example = new FundInfoExample();
        FundInfoExample.Criteria criteria = example.createCriteria();
        criteria.andFundCodeEqualTo(fundCode);
        return fundInfoMapper.selectOneByExample(example);
    }
}
