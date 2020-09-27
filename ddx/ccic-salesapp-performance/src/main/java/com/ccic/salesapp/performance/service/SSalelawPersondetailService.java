package com.ccic.salesapp.performance.service;

import com.ccic.salesapp.performance.domain.vo.dto.SSalelawPersondetail;

import java.util.List;
import java.util.Map;

/**
 * 考核保费及达成表(从基本法同步)
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:20:59
 */
public interface SSalelawPersondetailService {
	
	SSalelawPersondetail get(String usercode);
	
	List<SSalelawPersondetail> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SSalelawPersondetail sSalelawPersondetail);
	
	int update(SSalelawPersondetail sSalelawPersondetail);
	
	int remove(String usercode);
	
	int batchRemove(String[] usercodes);
}
