package com.ccic.salesapp.performance.domain.vo.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class UserMonthlyReportInfo {
	private int id;
	private String year;
	private String month;
	private String userCode;
	private String comCode;
	private String channelCode;
	private String userName;
	private BigDecimal totalPremium;
	private BigDecimal payreffeeY;
	private String rankingRate;
	private String rankingRateY;
	private String reachPerc;
	private BigDecimal payreffeeCar;
	private BigDecimal payreffeeAcc;
	private BigDecimal payreffeeHea;
	private BigDecimal payreffeePro;
	private int carNum;
	private int accNum;
	private int heaNum;
	private int proNum;
	private int signinNum;
	private int invoiceNum;
	private int interactionNum;
	private int comPerNum;
	private String pushStatus="0";
	private String signStatus="0";
	private Date createTime;
	private Date updateTime;
	private int monthStep;
}

