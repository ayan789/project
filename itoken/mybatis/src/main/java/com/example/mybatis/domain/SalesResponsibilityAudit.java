package com.example.mybatis.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;


/**
 * 
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-16 14:20:32
 */
@Data
public class SalesResponsibilityAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//业务员工号
	private String userCode;
	//所属机构
	private String deptCode;
	//所属渠道
	private String channelCode;
	//职位等级
	private String rankCode;
	//年目标总保费
	private String annualTotalPremium;
	//审核人id
	private String auditUserCode;
	//审核人姓名
	private String auditUserName;
	//顺序号
	private Integer auditSort;
	//审核权限
	private String auditRole;
	//责任书ID
	private String responsibilityId;
	//审核日期
	private Date auditTime;
	//审核意见
	private String auditRemark;
	//审核结果(0未审核，1审核通过，2不通过)
	private String auditResult;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//最后修改时间
	private Date lastUpdateTime;
	//创建人
	private String createBy;
	//修改人
	private Date lastUpdateBy;
	//状态
	private String status;
	//备注
	private String desc;

}
