package com.ccic.salesapp.performance.service;

import com.ccic.salesapp.performance.domain.vo.dto.SPrpsplanfeeDay;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 每日实收保费统计表(从基本法同步)
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:09:19
 */
public interface SPrpsplanfeeDayService {
	
	SPrpsplanfeeDay get(BigDecimal id);
	
	List<SPrpsplanfeeDay> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SPrpsplanfeeDay sPrpsplanfeeDay);
	
	int update(SPrpsplanfeeDay sPrpsplanfeeDay);
	
	int remove(BigDecimal id);
	
	int batchRemove(BigDecimal[] ids);
}
