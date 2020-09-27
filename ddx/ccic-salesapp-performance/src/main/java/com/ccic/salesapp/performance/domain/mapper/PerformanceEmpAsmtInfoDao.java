package com.ccic.salesapp.performance.domain.mapper;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ccic.salesapp.performance.domain.vo.dto.PerformanceEmpAsmtInfo;
import com.ccic.salesapp.performance.domain.vo.response.CheckRankResponse;

/**
 * 员工当月考核信息表
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-03-24 20:44:09
 */
@Mapper
@Component
public interface PerformanceEmpAsmtInfoDao {

	PerformanceEmpAsmtInfo get(Long id);
	
	List<PerformanceEmpAsmtInfo> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PerformanceEmpAsmtInfo performanceEmpAsmtInfo);
	
	int update(PerformanceEmpAsmtInfo performanceEmpAsmtInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	CheckRankResponse selectRankCheck(String userCode);
}
