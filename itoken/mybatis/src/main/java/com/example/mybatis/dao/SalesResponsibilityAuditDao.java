package com.example.mybatis.dao;

import com.example.mybatis.domain.SalesResponsibilityAudit;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-16 14:20:32
 */
@Mapper
@Component
public interface SalesResponsibilityAuditDao {

	SalesResponsibilityAudit get(String id);
	
	List<SalesResponsibilityAudit> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SalesResponsibilityAudit salesResponsibilityAudit);
	
	int update(SalesResponsibilityAudit salesResponsibilityAudit);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
