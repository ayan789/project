package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 消息表
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 10:46:25
 */
@ApiModel("消息管理")
public class SMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("物理主键")
	private BigDecimal id;
	@ApiModelProperty("员工编号")
	private String usercode;
	@ApiModelProperty("读取状态 0：未读  1：已读 9：删除")
	private String status;
	@ApiModelProperty("消息主体内容")
	private String message;
	@ApiModelProperty("消息类型")
	private String messageType;
	@ApiModelProperty("消息生成日期")
	private Date createDate;
	@ApiModelProperty("消息更新日期")
	private Date updateDate;
	@ApiModelProperty("保单号")
	private String policyNo;

	/**
	 * 设置：物理主键
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}
	/**
	 * 获取：物理主键
	 */
	public BigDecimal getId() {
		return id;
	}
	/**
	 * 设置：员工编号
	 */
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	/**
	 * 获取：员工编号
	 */
	public String getUsercode() {
		return usercode;
	}
	/**
	 * 设置：读取状态 0：未读  1：已读 9：删除
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：读取状态 0：未读  1：已读 9：删除
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：消息主体内容
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 获取：消息主体内容
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 设置：消息类型
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	/**
	 * 获取：消息类型
	 */
	public String getMessageType() {
		return messageType;
	}
	/**
	 * 设置：消息生成日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：消息生成日期
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：消息更新日期
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：消息更新日期
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：保单号
	 */
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	/**
	 * 获取：保单号
	 */
	public String getPolicyNo() {
		return policyNo;
	}
}
