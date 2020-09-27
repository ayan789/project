package com.ccic.salesapp.performance.domain.mapper;

import com.ccic.salesapp.performance.domain.vo.dto.SaleLawPersonDetail;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface B2BBUSI_T_APP_S_SALELAW_PERSONDETAIL_Mapper {
    SaleLawPersonDetail selectByUserCode(Map paramMap);
}
