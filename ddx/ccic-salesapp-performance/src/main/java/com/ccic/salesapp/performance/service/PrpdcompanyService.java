package com.ccic.salesapp.performance.service;

import com.ccic.salesapp.performance.domain.vo.dto.Prpdcompany;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:46:20
 */
public interface PrpdcompanyService {
	
	Prpdcompany get(String comcode);
	
	List<Prpdcompany> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Prpdcompany prpdcompany);
	
	int update(Prpdcompany prpdcompany);
	
	int remove(String comcode);
	
	int batchRemove(String[] comcodes);
}
