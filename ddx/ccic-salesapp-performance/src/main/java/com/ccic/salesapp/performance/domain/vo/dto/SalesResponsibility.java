package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 责任书
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 09:50:57
 */
@ApiModel("责任书")
public class SalesResponsibility implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("主键")
	private String id;
	
	@NotBlank(message = "业务员工号不能为空")
	@ApiModelProperty("业务员工号:签署人")
	private String userCode;
	
	@ApiModelProperty("责任书类型")
	private String responsibilityType;
	
	@ApiModelProperty("责任书名称")
	private String responsibilityName;
	
	@ApiModelProperty("责任书内容")
	private String responsibilityNote;
	
	@NotNull(message = "车险保费不能为空")
	@ApiModelProperty("车险保费    （年目标）")
	private BigDecimal carInsurePremium;
	
	@NotNull(message = "意外险保费不能为空")
	@ApiModelProperty("意外险保费（年目标）")
	private BigDecimal accInsurePremium;
	
	@NotNull(message = "健康险保费不能为空")
	@ApiModelProperty("健康险保费（年目标）")
	private BigDecimal heaInsurePremium;
	
	@NotNull(message = "财产险保费不能为空")
	@ApiModelProperty("财产险保费（年目标）")
	private BigDecimal propInsurePremium;
	
	@NotBlank(message = "一月总保费不能为空")
	@ApiModelProperty("一月总保费（月目标）")
	private String janTotalPremium;
	
	@NotBlank(message = "二月总保费不能为空")
	@ApiModelProperty("二月总保费（月目标）")
	private String febTotalPremium;

	@NotBlank(message = "三月总保费不能为空")
	@ApiModelProperty("三月总保费（月目标）")
	private String marTotalPremium;
	
	@NotBlank(message = "四月总保费不能为空")
	@ApiModelProperty("四月总保费（月目标）")
	private String aprTotalPremium;
	
	@NotBlank(message = "五月总保费不能为空")
	@ApiModelProperty("五月总保费（月目标）")
	private String mayTotalPremium;
	
	@NotBlank(message = "六月总保费不能为空")
	@ApiModelProperty("六月总保费（月目标）")
	private String junTotalPremium;
	
	@NotBlank(message = "七月总保费不能为空")
	@ApiModelProperty("七月总保费（月目标）")
	private String julTotalPremium;
	
	@NotBlank(message = "八月总保费不能为空")
	@ApiModelProperty("八月总保费（月目标）")
	private String augTotalPremium;
	
	@NotBlank(message = "九月总保费不能为空")
	@ApiModelProperty("九月总保费（月目标）")
	private String sepTotalPremium;
	
	@NotBlank(message = "十月总保费不能为空")
	@ApiModelProperty("十月总保费（月目标）")
	private String octTotalPremium;
	
	@NotBlank(message = "十一月总保费不能为空")
	@ApiModelProperty("十一月总保费（月目标）")
	private String novTotalPremium;
	
	@NotBlank(message = "十二月总保费不能为空")
	@ApiModelProperty("十二月总保费（月目标）")
	private String decTotalPremium;
	
	@ApiModelProperty("职位等级")
	private String rankCode;
	@ApiModelProperty("变更次数")
	private Integer multiple;
	@ApiModelProperty("领导签名图片URL")
	private String leaderSignUrl;
	@ApiModelProperty("个人签名图片URL")
	private String userSignUrl;
	@ApiModelProperty("责任书URL")
	private String responsibilityUrl;
	@ApiModelProperty("签署状态")
	private String signStatus;
	@ApiModelProperty("签署时间")
	private Date signTime;
	@ApiModelProperty("分享 :默认支持：Y   不支持：N   ")
	private String shareFlag;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("修改时间")
	private Date updateTime;
	@ApiModelProperty("最后修改时间")
	private Date lastUpdateTime;
	@ApiModelProperty("创建人")
	private String createBy;
	@ApiModelProperty("修改人")
	private String lastUpdateBy;
	@ApiModelProperty("状态")
	private String status;
	@ApiModelProperty("备注")
	private String desc;
	@ApiModelProperty("变更状态")
	private String changeStatus;

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
	 * 设置：业务员工号:签署人
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * 获取：业务员工号:签署人
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * 设置：责任书名称
	 */
	public void setResponsibilityName(String responsibilityName) {
		this.responsibilityName = responsibilityName;
	}
	/**
	 * 获取：责任书名称
	 */
	public String getResponsibilityName() {
		return responsibilityName;
	}
	/**
	 * 设置：责任书内容
	 */
	public void setResponsibilityNote(String responsibilityNote) {
		this.responsibilityNote = responsibilityNote;
	}
	/**
	 * 获取：责任书内容
	 */
	public String getResponsibilityNote() {
		return responsibilityNote;
	}
	/**
	 * 设置：车险保费    （年目标）
	 */
	public void setCarInsurePremium(BigDecimal carInsurePremium) {
		this.carInsurePremium = carInsurePremium;
	}
	/**
	 * 获取：车险保费    （年目标）
	 */
	public BigDecimal getCarInsurePremium() {
		return carInsurePremium;
	}
	/**
	 * 设置：意外险保费（年目标）
	 */
	public void setAccInsurePremium(BigDecimal accInsurePremium) {
		this.accInsurePremium = accInsurePremium;
	}
	/**
	 * 获取：意外险保费（年目标）
	 */
	public BigDecimal getAccInsurePremium() {
		return accInsurePremium;
	}
	/**
	 * 设置：健康险保费（年目标）
	 */
	public void setHeaInsurePremium(BigDecimal heaInsurePremium) {
		this.heaInsurePremium = heaInsurePremium;
	}
	/**
	 * 获取：健康险保费（年目标）
	 */
	public BigDecimal getHeaInsurePremium() {
		return heaInsurePremium;
	}
	/**
	 * 设置：财产险保费（年目标）
	 */
	public void setPropInsurePremium(BigDecimal propInsurePremium) {
		this.propInsurePremium = propInsurePremium;
	}
	/**
	 * 获取：财产险保费（年目标）
	 */
	public BigDecimal getPropInsurePremium() {
		return propInsurePremium;
	}
	/**
	 * 设置：一月总保费（月目标）
	 */
	public void setJanTotalPremium(String janTotalPremium) {
		this.janTotalPremium = janTotalPremium;
	}
	/**
	 * 获取：一月总保费（月目标）
	 */
	public String getJanTotalPremium() {
		return janTotalPremium;
	}
	/**
	 * 设置：二月总保费（月目标）
	 */
	public void setFebTotalPremium(String febTotalPremium) {
		this.febTotalPremium = febTotalPremium;
	}
	/**
	 * 获取：二月总保费（月目标）
	 */
	public String getFebTotalPremium() {
		return febTotalPremium;
	}
	/**
	 * 设置：三月总保费（月目标）
	 */
	public void setMarTotalPremium(String marTotalPremium) {
		this.marTotalPremium = marTotalPremium;
	}
	/**
	 * 获取：三月总保费（月目标）
	 */
	public String getMarTotalPremium() {
		return marTotalPremium;
	}
	/**
	 * 设置：四月总保费（月目标）
	 */
	public void setAprTotalPremium(String aprTotalPremium) {
		this.aprTotalPremium = aprTotalPremium;
	}
	/**
	 * 获取：四月总保费（月目标）
	 */
	public String getAprTotalPremium() {
		return aprTotalPremium;
	}
	/**
	 * 设置：五月总保费（月目标）
	 */
	public void setMayTotalPremium(String mayTotalPremium) {
		this.mayTotalPremium = mayTotalPremium;
	}
	/**
	 * 获取：五月总保费（月目标）
	 */
	public String getMayTotalPremium() {
		return mayTotalPremium;
	}
	/**
	 * 设置：六月总保费（月目标）
	 */
	public void setJunTotalPremium(String junTotalPremium) {
		this.junTotalPremium = junTotalPremium;
	}
	/**
	 * 获取：六月总保费（月目标）
	 */
	public String getJunTotalPremium() {
		return junTotalPremium;
	}
	/**
	 * 设置：七月总保费（月目标）
	 */
	public void setJulTotalPremium(String julTotalPremium) {
		this.julTotalPremium = julTotalPremium;
	}
	/**
	 * 获取：七月总保费（月目标）
	 */
	public String getJulTotalPremium() {
		return julTotalPremium;
	}
	/**
	 * 设置：八月总保费（月目标）
	 */
	public void setAugTotalPremium(String augTotalPremium) {
		this.augTotalPremium = augTotalPremium;
	}
	/**
	 * 获取：八月总保费（月目标）
	 */
	public String getAugTotalPremium() {
		return augTotalPremium;
	}
	/**
	 * 设置：九月总保费（月目标）
	 */
	public void setSepTotalPremium(String sepTotalPremium) {
		this.sepTotalPremium = sepTotalPremium;
	}
	/**
	 * 获取：九月总保费（月目标）
	 */
	public String getSepTotalPremium() {
		return sepTotalPremium;
	}
	/**
	 * 设置：十月总保费（月目标）
	 */
	public void setOctTotalPremium(String octTotalPremium) {
		this.octTotalPremium = octTotalPremium;
	}
	/**
	 * 获取：十月总保费（月目标）
	 */
	public String getOctTotalPremium() {
		return octTotalPremium;
	}
	/**
	 * 设置：十一月总保费（月目标）
	 */
	public void setNovTotalPremium(String novTotalPremium) {
		this.novTotalPremium = novTotalPremium;
	}
	/**
	 * 获取：十一月总保费（月目标）
	 */
	public String getNovTotalPremium() {
		return novTotalPremium;
	}
	/**
	 * 设置：十二月总保费（月目标）
	 */
	public void setDecTotalPremium(String decTotalPremium) {
		this.decTotalPremium = decTotalPremium;
	}
	/**
	 * 获取：十二月总保费（月目标）
	 */
	public String getDecTotalPremium() {
		return decTotalPremium;
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
	 * 设置：变更次数
	 */
	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}
	/**
	 * 获取：变更次数
	 */
	public Integer getMultiple() {
		return multiple;
	}
	/**
	 * 设置：领导签名图片URL
	 */
	public void setLeaderSignUrl(String leaderSignUrl) {
		this.leaderSignUrl = leaderSignUrl;
	}
	/**
	 * 获取：领导签名图片URL
	 */
	public String getLeaderSignUrl() {
		return leaderSignUrl;
	}
	/**
	 * 设置：个人签名图片URL
	 */
	public void setUserSignUrl(String userSignUrl) {
		this.userSignUrl = userSignUrl;
	}
	/**
	 * 获取：个人签名图片URL
	 */
	public String getUserSignUrl() {
		return userSignUrl;
	}
	/**
	 * 设置：责任书URL
	 */
	public void setResponsibilityUrl(String responsibilityUrl) {
		this.responsibilityUrl = responsibilityUrl;
	}
	/**
	 * 获取：责任书URL
	 */
	public String getResponsibilityUrl() {
		return responsibilityUrl;
	}
	/**
	 * 设置：签署状态
	 */
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	/**
	 * 获取：签署状态
	 */
	public String getSignStatus() {
		return signStatus;
	}
	/**
	 * 设置：签署时间
	 */
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	/**
	 * 获取：签署时间
	 */
	public Date getSignTime() {
		return signTime;
	}
	/**
	 * 设置：分享 :默认支持：Y   不支持：N   
	 */
	public void setShareFlag(String shareFlag) {
		this.shareFlag = shareFlag;
	}
	/**
	 * 获取：分享 :默认支持：Y   不支持：N   
	 */
	public String getShareFlag() {
		return shareFlag;
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
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	/**
	 * 获取：修改人
	 */
	public String getLastUpdateBy() {
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
	/**
	 * 设置：变更状态
	 */
	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}
	/**
	 * 获取：变更状态
	 */
	public String getChangeStatus() {
		return changeStatus;
	}
	public String getResponsibilityType() {
		return responsibilityType;
	}
	public void setResponsibilityType(String responsibilityType) {
		this.responsibilityType = responsibilityType;
	}
	
	
}
