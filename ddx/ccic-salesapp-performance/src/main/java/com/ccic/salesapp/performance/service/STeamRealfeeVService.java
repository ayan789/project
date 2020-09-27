package com.ccic.salesapp.performance.service;

import com.ccic.salesapp.performance.domain.vo.dto.STeamRealfeeV;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:29:20
 */
public interface STeamRealfeeVService {
	
	STeamRealfeeV get(String yearMonth);
	
	List<STeamRealfeeV> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(STeamRealfeeV sTeamRealfeeV);
	
	int update(STeamRealfeeV sTeamRealfeeV);
	
	int remove(String yearMonth);
	
	int batchRemove(String[] yearMonths);
}
