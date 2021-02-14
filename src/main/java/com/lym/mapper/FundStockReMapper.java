package com.lym.mapper;

import com.lym.domain.FundStockRe;
import com.lym.domain.FundStockReExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Table Name : t_fund_stock_re
 * Created by Tustrip Mybatis Generator on 2021/02/14
 */
public interface FundStockReMapper {
    long countByExample(FundStockReExample example);

    int deleteByExample(FundStockReExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FundStockRe record);

    int insertSelective(FundStockRe record);

    List<FundStockRe> selectByExampleSelective(@Param("example") FundStockReExample example, @Param("selective") FundStockRe.Column ... selective);

    FundStockRe selectOneByExample(FundStockReExample example);

    FundStockRe selectOneByExampleSelective(@Param("example") FundStockReExample example, @Param("selective") FundStockRe.Column ... selective);

    List<FundStockRe> selectByExample(FundStockReExample example);

    FundStockRe selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") FundStockRe.Column ... selective);

    FundStockRe selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FundStockRe record, @Param("example") FundStockReExample example);

    int updateByExample(@Param("record") FundStockRe record, @Param("example") FundStockReExample example);

    int updateByPrimaryKeySelective(FundStockRe record);

    int updateByPrimaryKey(FundStockRe record);

    int batchInsert(@Param("list") List<FundStockRe> list);

    int batchInsertSelective(@Param("list") List<FundStockRe> list, @Param("selective") FundStockRe.Column ... selective);
}