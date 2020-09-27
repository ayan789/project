package com.ccic.salesapp.performance.domain.mapper;

import com.ccic.salesapp.performance.domain.vo.dto.SPrpsplanfeeDay;
import com.ccic.salesapp.performance.domain.vo.request.PerformanceQueryTypeRequest;
import com.ccic.salesapp.performance.domain.vo.response.PerformanceResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 每日实收保费统计表(从基本法同步)
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:09:19
 */
@Mapper
@Component
public interface SPrpsplanfeeDayDao {

	SPrpsplanfeeDay get(BigDecimal id);
	
	List<SPrpsplanfeeDay> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SPrpsplanfeeDay sPrpsplanfeeDay);
	
	int update(SPrpsplanfeeDay sPrpsplanfeeDay);
	
	int remove(BigDecimal ID);
	
	int batchRemove(BigDecimal[] ids);
	
	PerformanceResponse selectByUserCodeDate(Map map);
	    
	PerformanceResponse selectTeamPerformanceByUserCodeDate(Map map);
	
	PerformanceResponse getPerformanceByQueryType(PerformanceQueryTypeRequest request);
}
