package com.example.mybatis.dao;

import com.example.mybatis.domain.SalesResponsibility;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-16 15:45:42
 */
@Mapper
@Component
public interface SalesResponsibilityDao {

	SalesResponsibility get(String id);
	
	List<SalesResponsibility> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SalesResponsibility salesResponsibility);
	
	int update(SalesResponsibility salesResponsibility);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}
