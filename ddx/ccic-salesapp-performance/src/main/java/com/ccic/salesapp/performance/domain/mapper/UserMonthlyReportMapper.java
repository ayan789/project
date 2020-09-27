package com.ccic.salesapp.performance.domain.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.ccic.salesapp.performance.domain.vo.dto.UserMonthlyReportInfo;

@Mapper
@Component
public interface UserMonthlyReportMapper {
	
	List<UserMonthlyReportInfo> selectUserStories(HashMap<String,Object> map);
	
	UserMonthlyReportInfo selectUserStoriesDetail(HashMap<String,Object> map);
	
	UserMonthlyReportInfo selectPremiumNum(HashMap<String,Object> map);
	
	HashMap<String, Object> selectRespPremium(HashMap<String,Object> map);
	
	UserMonthlyReportInfo selectUserComNum(HashMap<String,Object> map);
	
	Integer insertList(List<UserMonthlyReportInfo> userStories);
	
	List<String> selectUserCodes(HashMap<String,Object> map);
	
//	Integer selectMonthlyReportStatus(UserMonthlyReportInfo userStorie);
	
	UserMonthlyReportInfo selectUserInfo(UserMonthlyReportInfo userStorie);
	
	List<String> selectRank(HashMap<String,Object> map);
	
	List<String> selectMonthlyReportUserCodes(HashMap<String,Object> map);
	
	int updateMonthlyReportMsgStatus(HashMap<String,Object> map);
	
	int selectUserCodesNum(HashMap<String,Object> map);
	
	int selectMonthlyReportUserCodesNum(HashMap<String,Object> map);
	
	UserMonthlyReportInfo selectUserSignStatus(HashMap<String,Object> map);
	
//	int deleteMonthlyReportDirtyData(HashMap<String,Object> map);
	
}
