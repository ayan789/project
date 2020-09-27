package com.ccic.salesapp.performance.service;

import com.ccic.salesapp.performance.domain.vo.dto.SSalelawPerson;

import java.util.List;
import java.util.Map;

/**
 * 人员信息表(从基本法同步)
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:16:31
 */
public interface SSalelawPersonService {
	
	SSalelawPerson get(String usercode);
	
	List<SSalelawPerson> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SSalelawPerson sSalelawPerson);
	
	int update(SSalelawPerson sSalelawPerson);
	
	int remove(String usercode);
	
	int batchRemove(String[] usercodes);
}
