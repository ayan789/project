package com.ccic.salesapp.performance.domain.mapper;

import com.ccic.salesapp.performance.domain.vo.dto.SalesResponsibilityAudit;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ccic.salesapp.performance.domain.vo.dto.SalesResponsibilityAudit;

/**
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 10:25:16
 */
@Mapper
@Component
public interface SalesResponsibilityAuditDao {

	SalesResponsibilityAudit get(String id);
	
	List<SalesResponsibilityAudit> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SalesResponsibilityAudit salesResponsibilityAudit);
	
	int update(SalesResponsibilityAudit salesResponsibilityAudit);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
