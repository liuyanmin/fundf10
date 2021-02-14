package com.lym.mapper;

import com.lym.domain.FundInfo;
import com.lym.domain.FundInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Table Name : t_fund_info
 * Created by Tustrip Mybatis Generator on 2021/02/14
 */
public interface FundInfoMapper {
    long countByExample(FundInfoExample example);

    int deleteByExample(FundInfoExample example);

    int deleteByPrimaryKey(String fundCode);

    int insert(FundInfo record);

    int insertSelective(FundInfo record);

    List<FundInfo> selectByExampleSelective(@Param("example") FundInfoExample example, @Param("selective") FundInfo.Column ... selective);

    FundInfo selectOneByExample(FundInfoExample example);

    FundInfo selectOneByExampleSelective(@Param("example") FundInfoExample example, @Param("selective") FundInfo.Column ... selective);

    List<FundInfo> selectByExample(FundInfoExample example);

    FundInfo selectByPrimaryKeySelective(@Param("fundCode") String fundCode, @Param("selective") FundInfo.Column ... selective);

    FundInfo selectByPrimaryKey(String fundCode);

    int updateByExampleSelective(@Param("record") FundInfo record, @Param("example") FundInfoExample example);

    int updateByExample(@Param("record") FundInfo record, @Param("example") FundInfoExample example);

    int updateByPrimaryKeySelective(FundInfo record);

    int updateByPrimaryKey(FundInfo record);

    int batchInsert(@Param("list") List<FundInfo> list);

    int batchInsertSelective(@Param("list") List<FundInfo> list, @Param("selective") FundInfo.Column ... selective);
}