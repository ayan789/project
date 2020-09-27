package com.ccic.salesapp.performance.domain.mapper;

import com.ccic.salesapp.performance.domain.vo.dto.STeamRealfeeV;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:29:20
 */
@Mapper
@Component
public interface STeamRealfeeVDao {

	STeamRealfeeV get(String yearMonth);
	
	List<STeamRealfeeV> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(STeamRealfeeV sTeamRealfeeV);
	
	int update(STeamRealfeeV sTeamRealfeeV);
	
	int remove(String YEAR_MONTH);
	
	int batchRemove(String[] yearMonths);
}
