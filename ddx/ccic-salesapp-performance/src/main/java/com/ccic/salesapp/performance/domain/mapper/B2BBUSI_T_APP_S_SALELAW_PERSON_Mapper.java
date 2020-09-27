package com.ccic.salesapp.performance.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.performance.domain.vo.dto.SalelawPerson;

@Mapper
public interface B2BBUSI_T_APP_S_SALELAW_PERSON_Mapper {
  SalelawPerson selectByUserCode(String userCode);

  Double selectRenewalRateByUserCode(String userCode);
}
