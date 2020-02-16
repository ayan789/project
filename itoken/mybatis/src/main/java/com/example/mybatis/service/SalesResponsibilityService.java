package com.example.mybatis.service;

import com.example.mybatis.domain.SalesResponsibility;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-16 15:45:42
 */
public interface SalesResponsibilityService {
	
	SalesResponsibility get(String id);
	
	List<SalesResponsibility> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SalesResponsibility salesResponsibility);
	
	int update(SalesResponsibility salesResponsibility);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
