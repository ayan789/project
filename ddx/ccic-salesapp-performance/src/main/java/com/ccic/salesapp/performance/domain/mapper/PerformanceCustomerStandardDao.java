package com.ccic.salesapp.performance.domain.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ccic.salesapp.performance.domain.vo.dto.PerformanceCustomerStandard;

/**
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-04-16 21:27:13
 */
@Mapper
@Component
public interface PerformanceCustomerStandardDao {

	PerformanceCustomerStandard get(Integer id);
	
	List<PerformanceCustomerStandard> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PerformanceCustomerStandard performanceCustomerStandard);
	
	int update(PerformanceCustomerStandard performanceCustomerStandard);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	PerformanceCustomerStandard getPerformanceCustomer(HashMap<String,Object> map);
	
	Double getGZCustomerNum(HashMap<String,Object> map);
	
	Double getFGZCustomerNum(HashMap<String,Object> map);
}
