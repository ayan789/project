package com.example.tlyannoif;

import org.apache.ibatis.annotations.Mapper;

import com.example.tlyannoif.CoinInfo;

@Mapper
public interface CoinInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CoinInfo record);

    int insertSelective(CoinInfo record);

    CoinInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoinInfo record);

    int updateByPrimaryKey(CoinInfo record);
}