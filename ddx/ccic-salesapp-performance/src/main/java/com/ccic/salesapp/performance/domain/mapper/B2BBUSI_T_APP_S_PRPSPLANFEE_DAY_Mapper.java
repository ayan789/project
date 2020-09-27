package com.ccic.salesapp.performance.domain.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.performance.domain.vo.dto.PrpsPlanFeeDay;
import com.ccic.salesapp.performance.domain.vo.response.PerformanceResponse;

@Mapper
public interface B2BBUSI_T_APP_S_PRPSPLANFEE_DAY_Mapper {
    PrpsPlanFeeDay selectByUserCode(Map paramMap);

    PrpsPlanFeeDay  selectByUserCodeMonth(Map paramMap);

    PrpsPlanFeeDay selectByUserCodeYear(Map parmMap);
    
    PerformanceResponse selectByUserCodeDate(Map paramMap);
    
    PerformanceResponse selectTeamPerformanceByUserCodeDate(Map paramMap);
}
