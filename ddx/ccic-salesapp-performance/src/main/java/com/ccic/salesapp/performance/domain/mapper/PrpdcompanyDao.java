package com.ccic.salesapp.performance.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ccic.salesapp.performance.domain.vo.dto.Prpdcompany;

/**
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:46:20
 */
@Mapper
@Component
public interface PrpdcompanyDao {

	Prpdcompany get(String comcode);
	
	List<Prpdcompany> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(Prpdcompany prpdcompany);
	
	int update(Prpdcompany prpdcompany);
	
	int remove(String COMCODE);
	
	int batchRemove(String[] comcodes);
}
