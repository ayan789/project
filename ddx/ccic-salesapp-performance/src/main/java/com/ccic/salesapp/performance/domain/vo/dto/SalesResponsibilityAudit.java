package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 责任书审批
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 10:25:16
 */
@ApiModel("责任书审批")
public class SalesResponsibilityAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("主键")
	private String id;
	@ApiModelProperty("业务员工号")
	private String userCode;
	@ApiModelProperty("所属机构")
	private String deptCode;
	@ApiModelProperty("所属渠道")
	private String channelCode;
	@ApiModelProperty("职位等级")
	private String rankCode;
	@ApiModelProperty("年目标总保费")
	private String annualTotalPremium;
	@ApiModelProperty("审核人id")
	private String auditUserCode;
	@ApiModelProperty("审核人姓名")
	private String auditUserName;
	@ApiModelProperty("顺序号")
	private Integer auditSort;
	@ApiModelProperty("审核权限")
	private String auditRole;
	@ApiModelProperty("责任书ID")
	private String responsibilityId;
	@ApiModelProperty("审核日期")
	private Date auditTime;
	@ApiModelProperty("审核意见")
	private String auditRemark;
	@ApiModelProperty("审核结果(0未审核，1审核通过，2不通过)")
	private String auditResult;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("修改时间")
	private Date updateTime;
	@ApiModelProperty("最后修改时间")
	private Date lastUpdateTime;
	@ApiModelProperty("创建人")
	private String createBy;
	@ApiModelProperty("修改人")
	private Date lastUpdateBy;
	@ApiModelProperty("状态")
	private String status;
	@ApiModelProperty("备注")
	private String desc;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：业务员工号
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * 获取：业务员工号
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * 设置：所属机构
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	/**
	 * 获取：所属机构
	 */
	public String getDeptCode() {
		return deptCode;
	}
	/**
	 * 设置：所属渠道
	 */
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	/**
	 * 获取：所属渠道
	 */
	public String getChannelCode() {
		return channelCode;
	}
	/**
	 * 设置：职位等级
	 */
	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}
	/**
	 * 获取：职位等级
	 */
	public String getRankCode() {
		return rankCode;
	}
	/**
	 * 设置：年目标总保费
	 */
	public void setAnnualTotalPremium(String annualTotalPremium) {
		this.annualTotalPremium = annualTotalPremium;
	}
	/**
	 * 获取：年目标总保费
	 */
	public String getAnnualTotalPremium() {
		return annualTotalPremium;
	}
	/**
	 * 设置：审核人id
	 */
	public void setAuditUserCode(String auditUserCode) {
		this.auditUserCode = auditUserCode;
	}
	/**
	 * 获取：审核人id
	 */
	public String getAuditUserCode() {
		return auditUserCode;
	}
	/**
	 * 设置：审核人姓名
	 */
	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	/**
	 * 获取：审核人姓名
	 */
	public String getAuditUserName() {
		return auditUserName;
	}
	/**
	 * 设置：顺序号
	 */
	public void setAuditSort(Integer auditSort) {
		this.auditSort = auditSort;
	}
	/**
	 * 获取：顺序号
	 */
	public Integer getAuditSort() {
		return auditSort;
	}
	/**
	 * 设置：审核权限
	 */
	public void setAuditRole(String auditRole) {
		this.auditRole = auditRole;
	}
	/**
	 * 获取：审核权限
	 */
	public String getAuditRole() {
		return auditRole;
	}
	/**
	 * 设置：责任书ID
	 */
	public void setResponsibilityId(String responsibilityId) {
		this.responsibilityId = responsibilityId;
	}
	/**
	 * 获取：责任书ID
	 */
	public String getResponsibilityId() {
		return responsibilityId;
	}
	/**
	 * 设置：审核日期
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	/**
	 * 获取：审核日期
	 */
	public Date getAuditTime() {
		return auditTime;
	}
	/**
	 * 设置：审核意见
	 */
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	/**
	 * 获取：审核意见
	 */
	public String getAuditRemark() {
		return auditRemark;
	}
	/**
	 * 设置：审核结果(0未审核，1审核通过，2不通过)
	 */
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	/**
	 * 获取：审核结果(0未审核，1审核通过，2不通过)
	 */
	public String getAuditResult() {
		return auditResult;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：最后修改时间
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 * 获取：最后修改时间
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：修改人
	 */
	public void setLastUpdateBy(Date lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	/**
	 * 获取：修改人
	 */
	public Date getLastUpdateBy() {
		return lastUpdateBy;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：备注
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：备注
	 */
	public String getDesc() {
		return desc;
	}
}
