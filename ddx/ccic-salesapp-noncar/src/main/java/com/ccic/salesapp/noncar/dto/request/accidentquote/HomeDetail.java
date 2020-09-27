package com.ccic.salesapp.noncar.dto.request.accidentquote;

import java.util.Date;
import java.util.Map;

import lombok.Data;
@Data
public class HomeDetail {
	private Date buildDate;// 建造时间/日期
	private int buildingArea;// 建筑面积
	private int buildingFloor;// 所在楼层
	private int buildingLayer;// 建筑层数
	private int buildingType;// 建筑类型
	private Date decorateDate;// 装修时间
	private String homeContractNo;// 购房契约号
	private String homePurposeCode;// 房屋用途（自住、租赁）
	private String homeStructure;// 房屋结构
	private String homeType;// 房屋类型（自住、商住两用、出租、其他）
	private String houseAddress;// 房屋地址（清单）
	private String housingType;// 住宅类型
	private String isDecorate;// 是否装修
	private Double loanAmount;// 贷款金额
	private String loanApproveNo;// 贷款审批表编号
	private String loanNatureCode;// 贷款性质
	private String loanOrg;// 贷款机构
	private String loanTerm;// 贷款期限
	private String pawnRegNo;// 抵押物登记表号
	private String propertyNo;// 产权证号
	private Double squareMeterCost;// 每平米造价
	private Double totalPrice;// 总价款
	private Map<String, String> tempData;// 临时数据
	private Long sequenceNumber;// 序号
}
