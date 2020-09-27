package com.ccic.salesapp.performance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccic.salesapp.performance.controller.MessageSendVO;
import com.ccic.salesapp.performance.domain.mapper.UserMonthlyReportMapper;
import com.ccic.salesapp.performance.domain.vo.dto.UserMonthlyReportInfo;
import com.ccic.salesapp.performance.feign.SupportFeignService;
import com.ccic.salesapp.performance.service.MonthlyReportTaskService;
import com.ccic.salessapp.common.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MonthlyReportTaskServiceImpl implements MonthlyReportTaskService {
	
	@Autowired
	UserMonthlyReportMapper userStorieMapper;
	
	@Autowired
	SupportFeignService supportFeignService;
	
	@Override
	@Transactional
	public void insertListService(List<String> userCodes,HashMap<String, Object> map,int step) {
		List<UserMonthlyReportInfo> userStories = new ArrayList<>();
		UserMonthlyReportInfo userStorie;
		UserMonthlyReportInfo userStorieBuild1;
		HashMap<String, Object> userStorieBuild2;
		UserMonthlyReportInfo userStorieBuild3;
		BigDecimal number = BigDecimal.valueOf((int)10000);
		BigDecimal deafaultValue = BigDecimal.valueOf((int)0);
		BigDecimal insurePremium;//月度目标总保费
		BigDecimal reachPerc;
	    for(int i=0;i<userCodes.size();i++) {
			map.put("userCode", userCodes.get(i));
			userStorie = new UserMonthlyReportInfo();
			//查询用户
			//insert月度报告数据
			userStorieBuild1 = userStorieMapper.selectPremiumNum(map);
			if(userStorieBuild1!=null) {
			    userStorie.setPayreffeeY(userStorieBuild1.getPayreffeeY().divide(number,2, BigDecimal.ROUND_HALF_UP));//本月实收保费 -万元
				userStorie.setTotalPremium(userStorieBuild1.getTotalPremium().divide(number,2, BigDecimal.ROUND_HALF_UP));//本月实收保费 -万元
				userStorie.setPayreffeeCar(userStorieBuild1.getPayreffeeCar().divide(number,2, BigDecimal.ROUND_HALF_UP));//实收保费-车险-万元
				userStorie.setPayreffeeAcc(userStorieBuild1.getPayreffeeAcc().divide(number,2, BigDecimal.ROUND_HALF_UP));//实收保费-意外-万元
				userStorie.setPayreffeeHea(userStorieBuild1.getPayreffeeHea().divide(number,2, BigDecimal.ROUND_HALF_UP));//实收保费-健康-万元
				userStorie.setPayreffeePro(userStorieBuild1.getPayreffeePro().divide(number,2, BigDecimal.ROUND_HALF_UP));//实收保费-财产-万元
				userStorie.setInvoiceNum(userStorieBuild1.getInvoiceNum());//累计总单数
				userStorie.setCarNum(userStorieBuild1.getCarNum());//累计总单数-车险
				userStorie.setHeaNum(userStorieBuild1.getHeaNum());//累计总单数-健康
				userStorie.setAccNum(userStorieBuild1.getAccNum());//累计总单数-意外
				userStorie.setProNum(userStorieBuild1.getProNum());//累计总单数-财产
				userStorie.setSignStatus(userStorieBuild1.getSignStatus());
				userStorie.setSigninNum(userStorieBuild1.getSigninNum());
				userStorie.setInteractionNum(userStorieBuild1.getInteractionNum());
				userStorieBuild2 = userStorieMapper.selectRespPremium(map);
				if("1".equals(userStorie.getSignStatus()) && userStorieBuild2!=null && userStorieBuild2.get("insurePremium")!=null) {
					insurePremium =  (BigDecimal) userStorieBuild2.get("insurePremium");
					reachPerc = userStorieBuild1.getPayreffeeY().divide(insurePremium,4, BigDecimal.ROUND_HALF_UP); //达成比
					userStorie.setReachPerc(reachPerc+"");
				}
				if("0".equals(userStorie.getSignStatus())) {
					userStorie.setReachPerc(null);
				}
				userStorie.setUserCode(userCodes.get(i));
				UserMonthlyReportInfo userInfo = userStorieMapper.selectUserInfo(userStorie);
				if(!StringUtil.isNullorEmpty(userInfo.getChannelCode())) {
					map.put("channelCode", userInfo.getChannelCode());
				}
				userStorieBuild3 = userStorieMapper.selectUserComNum(map);
				if(userStorieBuild3!=null) {
					userStorie.setUserCode(userStorieBuild3.getUserCode());
					userStorie.setUserName(userStorieBuild3.getUserName());
					if(userStorieBuild3!=null){
						userStorie.setComPerNum(userStorieBuild3.getComPerNum());
						UserMonthlyReportInfo userStorieBuild4 = userStorieMapper.selectUserComNum(map);
						userStorieBuild4 = userStorieMapper.selectUserInfo(userStorie);
						userStorie.setComCode(userStorieBuild4.getComCode());
						userStorie.setChannelCode(userStorieBuild4.getChannelCode());
						if(!StringUtil.isNullorEmpty(userStorieBuild4.getChannelCode())) {
							map.put("channelCode", userStorieBuild4.getChannelCode());
						}
						map.put("comCode", userStorieBuild4.getComCode());
						map.put("timeFlag", "Y");
						String rankingRateY = getRank(userStorie, userStorieBuild3, map);
						
						userStorie.setRankingRateY(rankingRateY);
						map.put("timeFlag", "M");
						String rankingRate = getRank(userStorie, userStorieBuild3, map);
						
						userStorie.setRankingRate(rankingRate);
					}
					userStorie.setYear(map.get("isYear")+"");
					userStorie.setMonth(map.get("monthStep")+"");
					log.info("第{}批，执行到{}行,用户{}",step+1,i+1,userCodes.get(i));
					userStories.add(userStorie);
				} 
				else {
					log.info("第{}批，执行到{}行,用户{}没有查到",step+1,i+1,userCodes.get(i));
				}
			}
			else {
				userStorie.setUserCode(userCodes.get(i));
				UserMonthlyReportInfo userInfo = userStorieMapper.selectUserInfo(userStorie);
				if(!StringUtil.isNullorEmpty(userInfo.getChannelCode())) {
					map.put("channelCode", userInfo.getChannelCode());
				}
				userStorieBuild3 = userStorieMapper.selectUserComNum(map);
				if(userStorieBuild3!=null) {
					userStorie.setUserCode(userStorieBuild3.getUserCode());
					userStorie.setUserName(userStorieBuild3.getUserName());
					userStorie.setYear(map.get("isYear")+"");
					userStorie.setMonth(map.get("monthStep")+"");
					userStorie.setTotalPremium(deafaultValue);
					userStorie.setPayreffeeY(deafaultValue);
					userStorie.setRankingRate("0");
					userStorie.setRankingRateY("0");
					UserMonthlyReportInfo userSign = userStorieMapper.selectUserSignStatus(map);
					if(userSign==null || "0".equals(userSign.getSignStatus())) {
						userStorie.setReachPerc(null);
					}
					else if("1".equals(userSign.getSignStatus())) {
						userStorie.setReachPerc("0");
					}
					userStorie.setPayreffeeCar(deafaultValue);
					userStorie.setPayreffeeAcc(deafaultValue);
					userStorie.setPayreffeeHea(deafaultValue);
					userStorie.setPayreffeePro(deafaultValue);
					userStorie.setCarNum(0);
					userStorie.setAccNum(0);
					userStorie.setHeaNum(0);
					userStorie.setProNum(0);
					userStorie.setSigninNum(0);
					userStorie.setInvoiceNum(0);
					userStorie.setInteractionNum(0);
					userStorie.setComPerNum(0);
					userStorie.setPushStatus("0");
					userStorie.setSignStatus("0");
					userStorie.setMonthStep(0);
					userStories.add(userStorie);
					log.info("第{}批，执行到{}行,用户{}基本法未推送当日数据",step+1,i+1,userCodes.get(i));
				}
			}
		}
    	if(userStories!=null && userStories.size()>0) userStorieMapper.insertList(userStories);
    	else {
    		System.out.println("kong");
    	}
	}
 
    public String getRank(UserMonthlyReportInfo userStorie,UserMonthlyReportInfo userStorieBuild3,HashMap<String, Object> map) {
    	List<String> strList = userStorieMapper.selectRank(map);
		int idx = 0;
		for (int k=0;k<strList.size();k++){
		     if(strList.get(k).equals(userStorie.getUserCode())){
		    	 idx = k+1;
		     }
		}	
		//获取分公司当月底排名
		if(idx>0) {
			System.out.println(userStorieBuild3.getComPerNum());
			System.out.println(Double.valueOf(idx));
			return String.format("%.4f", (userStorieBuild3.getComPerNum()- Double.valueOf(idx))/userStorieBuild3.getComPerNum());
		}
		else return "0";
	}

	@Override
	@Transactional
	public void updateListSendMsgService(List<String> userCodes, HashMap<String, Object> map) {
		String msg = map.get("month") + "月的月度报告已生成，请亲启"; 
		List<MessageSendVO> list = new ArrayList<>();
		// 调用消息通知服务
		MessageSendVO messageSendVO = new MessageSendVO();
        messageSendVO.setMessage(msg);
        messageSendVO.setMessageType("push_month_report");//消息类型编码
        messageSendVO.setUserCode(userCodes.get(0));
        messageSendVO.setExtendMessage("");//页面链接跳转
        messageSendVO.setBizNo(map.get("month")+"_month_report");//业务编码
        messageSendVO.setUserCodes(userCodes);
        list.add(messageSendVO);
        supportFeignService.sendMessage(list);
		userStorieMapper.updateMonthlyReportMsgStatus(map);
	}
	
}
