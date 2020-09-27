package com.ccic.salesapp.performance.domain.mapper;


import com.ccic.salesapp.performance.domain.vo.dto.PerfResponsibilityDetail;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface PerfResponsibilityDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PerfResponsibilityDetail record);

    PerfResponsibilityDetail selectByPrimaryKey(Long id);

    List<PerfResponsibilityDetail> selectAll();

    int updateByPrimaryKey(PerfResponsibilityDetail record);
    
    List<PerfResponsibilityDetail> selectByPerfResponsibilityId(Long perfResponsibilityId);
}