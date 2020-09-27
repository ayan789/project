package com.ccic.salesapp.performance.service;

import org.springframework.web.bind.annotation.RequestBody;
import com.ccic.salesapp.performance.domain.vo.request.ProgressRequestVo;
import com.ccic.salesapp.performance.domain.vo.request.RankVo;
import com.ccic.salesapp.performance.domain.vo.request.SalesResponsibilityRequestVo;
import com.ccic.salesapp.performance.domain.vo.request.SalesResponsibilityVo;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface SalesResponsibilityNewService {
	//数据库结构改造
	//查询责任书列表,领导签名图片查询,获取责任书目标值
	HttpResult<?> salesResponsibilityList(SalesResponsibilityVo salesResponsibilityVo);
	//责任书保存更新
	HttpResult<?> salesResponsibilitySave(SalesResponsibilityRequestVo salesResponsibilityRequestVo);
	//月季度达成进度
	HttpResult<?> getMonthlyQuarterlyProgress(ProgressRequestVo progressRequestVo);
	//年度达成进度
	HttpResult<?> getAnnualProgress(ProgressRequestVo progressRequestVo);
	//业绩排名
	HttpResult<?> getpersAndTeamRanking(RankVo rankVo);
	//年度排名＋当月保费＋年度保费
	HttpResult<?> getRankAndPremium(RankVo rankVo);
	HttpResult<?> getM2YPremium(RankVo rankVo);
	//责任书初始化
	HttpResult<?> hasResponsibilityStatus(SalesResponsibilityRequestVo salesResponsibilityRequestVo);
	//排名和月总保费年总保费
	public HttpResult<?> queryRankingM2YPremium(@RequestBody RankVo rankVo);
	//消息发送到通知栏
	public HttpResult<?> responsibilityNoticePush(@RequestBody String[] userCodes);
	//消息发送到APP
	public HttpResult<?> responsibilityNotice(@RequestBody String userCode);
	//客户对标数
	public HttpResult<?> custStandardTotal();
	
}
