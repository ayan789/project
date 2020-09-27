package com.ccic.salesapp.performance.domain.mapper;

import com.ccic.salesapp.performance.domain.vo.dto.SStore;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:25:19
 */
@Mapper
@Component
public interface SStoreDao {

	SStore get(BigDecimal id);
	
	List<SStore> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SStore sStore);
	
	int update(SStore sStore);
	
	int remove(BigDecimal ID);
	
	int batchRemove(BigDecimal[] ids);
}
