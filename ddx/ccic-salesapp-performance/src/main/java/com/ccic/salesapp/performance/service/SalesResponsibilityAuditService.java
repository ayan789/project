package com.ccic.salesapp.performance.service;

import com.ccic.salesapp.performance.domain.vo.dto.SalesResponsibilityAudit;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 10:25:16
 */
public interface SalesResponsibilityAuditService {
	
	SalesResponsibilityAudit get(String id);
	
	List<SalesResponsibilityAudit> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SalesResponsibilityAudit salesResponsibilityAudit);
	
	int update(SalesResponsibilityAudit salesResponsibilityAudit);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
