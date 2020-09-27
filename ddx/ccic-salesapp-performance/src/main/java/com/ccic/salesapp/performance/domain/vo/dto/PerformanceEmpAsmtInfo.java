package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


/**
 * 员工当月考核信息表
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-03-24 20:44:09
 */
@Data
public class PerformanceEmpAsmtInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//人员工号
	private String empNo;
	//人员姓名
	private String empName;
	//计算年月
	private String yearmonth;
	//个人月标准保费合计
	private String policyPremiumTotal;
	//月标准保费计划
	private String policyPremiumPlan;
	//滚动12个月个人标准保费
	private String rolling12msStdPrem;
	//当年累计标准保费计划
	private String yearTotalStdPremPlan;
	//
	private Date createdDate;
	//
	private Date updateDate;

}
