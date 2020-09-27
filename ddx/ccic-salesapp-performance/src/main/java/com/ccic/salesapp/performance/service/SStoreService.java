package com.ccic.salesapp.performance.service;

import com.ccic.salesapp.performance.domain.vo.dto.SStore;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:25:19
 */
public interface SStoreService {
	
	SStore get(BigDecimal id);
	
	List<SStore> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SStore sStore);
	
	int update(SStore sStore);
	
	int remove(BigDecimal id);
	
	int batchRemove(BigDecimal[] ids);
}
