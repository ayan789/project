package com.ccic.salesapp.performance.service;



import java.util.List;
import java.util.Map;

import com.ccic.salesapp.performance.domain.vo.dto.PerformanceEmpAsmtInfo;
import com.ccic.salesapp.performance.domain.vo.response.CheckRankResponse;

/**
 * 员工当月考核信息表
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-03-24 20:44:09
 */
public interface PerformanceEmpAsmtInfoService {
	
	PerformanceEmpAsmtInfo get(Long id);
	
	List<PerformanceEmpAsmtInfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PerformanceEmpAsmtInfo performanceEmpAsmtInfo);
	
	int update(PerformanceEmpAsmtInfo performanceEmpAsmtInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	CheckRankResponse selectRankCheck(String userCode);
}
