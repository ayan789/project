package com.ccic.salesapp.performance.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ccic.salesapp.performance.domain.vo.dto.AppCheckCode;

@Mapper
public interface AppCheckCodeMapper {
	
	int upCheckCode(Map map);
	
	int insert(AppCheckCode appCheckCode);
	
	AppCheckCode selectCodeByUser(Map map);
	
	List<AppCheckCode> selectCodeByMoblieAndUser(Map map);
	
	int checkDoubleClickByMobile(String mobile);
	
	int checkDoubleClickByUserCode(String userCode);
}
