package com.ccic.salesapp.performance.domain.vo.dto;

import lombok.Data;


/**
 * 
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-04-16 21:27:13
 */
@Data
public class PerformanceCustomerStandard  {
	//
	private Integer id;
	//业务员
	private String userCode;
	//客户数
	private Integer customerTotal;
	//人均产能
	private Double capacity;
	//0:非直个 1:直个
	private String channel;

}
