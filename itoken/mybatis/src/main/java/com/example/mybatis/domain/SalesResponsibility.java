package com.example.mybatis.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;


/**
 * 
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-16 15:45:42
 */
@Data
public class SalesResponsibility implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String id;
	//业务员工号:签署人
	private String userCode;
	//责任书名称
	private String responsibilityName;
	//责任书内容
	private String responsibilityNote;
	//车险保费    （年目标）
	private BigDecimal carInsurePremium;
	//意外险保费（年目标）
	private BigDecimal accInsurePremium;
	//健康险保费（年目标）
	private BigDecimal heaInsurePremium;
	//财产险保费（年目标）
	private BigDecimal propInsurePremium;
	//一月总保费（月目标）
	private String janTotalPremium;
	//二月总保费（月目标）
	private String febTotalPremium;
	//三月总保费（月目标）
	private String marTotalPremium;
	//四月总保费（月目标）
	private String aprTotalPremium;
	//五月总保费（月目标）
	private String mayTotalPremium;
	//六月总保费（月目标）
	private String junTotalPremium;
	//七月总保费（月目标）
	private String julTotalPremium;
	//八月总保费（月目标）
	private String augTotalPremium;
	//九月总保费（月目标）
	private String sepTotalPremium;
	//十月总保费（月目标）
	private String octTotalPremium;
	//十一月总保费（月目标）
	private String novTotalPremium;
	//十二月总保费（月目标）
	private String decTotalPremium;
	//职位等级
	private String rankCode;
	//变更次数
	private Integer multiple;
	//领导签名图片URL
	private String leaderSignUrl;
	//个人签名图片URL
	private String userSignUrl;
	//责任书URL
	private String responsibilityUrl;
	//签署状态
	private String signStatus;
	//签署时间
	private Date signTime;
	//分享 :默认支持：Y   不支持：N   
	private String shareFlag;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//最后修改时间
	private Date lastUpdateTime;
	//创建人
	private String createBy;
	//修改人
	private String lastUpdateBy;
	//状态
	private String status;
	//备注
	private String desc;

}
