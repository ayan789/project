package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:25:19
 */
public class SStore implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private BigDecimal id;
	//门店代码
	private String storeCode;
	//门店名称
	private String storeName;
	//门店地区代码
	private String storeRegionCode;
	//门店状态   0-锁定   1-正常
	private String storeState;
	//业务员代码
	private String userCode;
	//业务员名称
	private String userName;
	//业务员出单机构
	private String userComcode;
	//业务员类型    1-业务员  2- 其他
	private String userType;
	//创建时间
	private Date createDate;
	//电话
	private BigDecimal userTel;

	/**
	 * 设置：
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getId() {
		return id;
	}
	/**
	 * 设置：门店代码
	 */
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	/**
	 * 获取：门店代码
	 */
	public String getStoreCode() {
		return storeCode;
	}
	/**
	 * 设置：门店名称
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	/**
	 * 获取：门店名称
	 */
	public String getStoreName() {
		return storeName;
	}
	/**
	 * 设置：门店地区代码
	 */
	public void setStoreRegionCode(String storeRegionCode) {
		this.storeRegionCode = storeRegionCode;
	}
	/**
	 * 获取：门店地区代码
	 */
	public String getStoreRegionCode() {
		return storeRegionCode;
	}
	/**
	 * 设置：门店状态   0-锁定   1-正常
	 */
	public void setStoreState(String storeState) {
		this.storeState = storeState;
	}
	/**
	 * 获取：门店状态   0-锁定   1-正常
	 */
	public String getStoreState() {
		return storeState;
	}
	/**
	 * 设置：业务员代码
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * 获取：业务员代码
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * 设置：业务员名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：业务员名称
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：业务员出单机构
	 */
	public void setUserComcode(String userComcode) {
		this.userComcode = userComcode;
	}
	/**
	 * 获取：业务员出单机构
	 */
	public String getUserComcode() {
		return userComcode;
	}
	/**
	 * 设置：业务员类型    1-业务员  2- 其他
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取：业务员类型    1-业务员  2- 其他
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：电话
	 */
	public void setUserTel(BigDecimal userTel) {
		this.userTel = userTel;
	}
	/**
	 * 获取：电话
	 */
	public BigDecimal getUserTel() {
		return userTel;
	}
}
