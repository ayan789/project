package com.ccic.salesapp.performance.service;

import com.ccic.salesapp.performance.domain.vo.dto.SUser;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户表
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:42:45
 */
public interface SUserService {
	
	SUser get(BigDecimal id);
	
	List<SUser> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SUser sUser);
	
	int update(SUser sUser);
	
	int remove(BigDecimal id);
	
	int batchRemove(BigDecimal[] ids);
	
	void updateSalaryPwd(SUser user);
	
	SUser selectByUserCode(String userCode);
}
