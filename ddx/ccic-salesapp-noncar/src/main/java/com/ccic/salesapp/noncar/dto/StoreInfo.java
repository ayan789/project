package com.ccic.salesapp.noncar.dto;

import lombok.Data;

import java.util.Date;


@Data
public class StoreInfo {
	private Long id;
	private String storeCode;			//门店代码
	private String storeName;			//门店名称
	private String storeRegionCode;		//门店地区代码
	private String storeState;			//门店状态
	private String userCode;			//业务员代码
	private String userName;			//业务员名称
	private String userComCode;			//业务员出单机构
	private String userType;			//业务员类型
	private Date createDate;			//创建时间
	private String userTel;				//业务员手机号
	
	
	

	
	
}
