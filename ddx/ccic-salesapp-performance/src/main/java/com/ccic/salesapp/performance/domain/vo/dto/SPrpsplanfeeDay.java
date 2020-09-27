package com.ccic.salesapp.performance.domain.vo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * 每日实收保费统计表(从基本法同步)
 * 
 * @author ys
 * @email yanshuai@fulan.com.cn
 * @date 2020-02-18 11:09:19
 */
@ApiModel("每日实收保费统计表(从基本法同步)")
public class SPrpsplanfeeDay implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("主键自增ID")
	private BigDecimal id;
	@ApiModelProperty("人员工号")
	private String usercode;
	@ApiModelProperty("实收日期")
	private Date realpaydate;
	@ApiModelProperty("实收保费（昨天实收）（含税）")
	private BigDecimal payreffee;
	@ApiModelProperty("实收保费-车险")
	private BigDecimal payreffeeCar;
	@ApiModelProperty("实收保费-非车险")
	private BigDecimal payreffeeNocar;
	@ApiModelProperty("实收保费-意外")
	private BigDecimal payreffeeAcc;
	@ApiModelProperty("实收保费-健康")
	private BigDecimal payreffeeHea;
	@ApiModelProperty("本月累计实收保费（月初到昨天）")
	private BigDecimal payreffeeMS;
	@ApiModelProperty("本月累计实收保费-车险")
	private BigDecimal payreffeeMSC;
	@ApiModelProperty("本月累计实收保费-非车险")
	private BigDecimal payreffeeMSNc;
	@ApiModelProperty("本月累计实收保费-意外")
	private BigDecimal payreffeeMSA;
	@ApiModelProperty("本月累计实收保费-健康")
	private BigDecimal payreffeeMSH;
	@ApiModelProperty("本年累计实收保费（年初到昨天）")
	private BigDecimal payreffeeYS;
	@ApiModelProperty("本年累计实收保费-车险")
	private BigDecimal payreffeeYSC;
	@ApiModelProperty("本年累计实收保费-非车险")
	private BigDecimal payreffeeYSNc;
	@ApiModelProperty("本年累计实收保费-意外")
	private BigDecimal payreffeeYSA;
	@ApiModelProperty("本年累计实收保费-健康")
	private BigDecimal payreffeeYSH;
	@ApiModelProperty("上月整月实收保费")
	private BigDecimal payreffeeLM;
	@ApiModelProperty("上月整月实收保费-车险")
	private BigDecimal payreffeeLMC;
	@ApiModelProperty("上月整月实收保费_非车险")
	private BigDecimal payreffeeLMNc;
	@ApiModelProperty("上月整月实收保费_意外险")
	private BigDecimal payreffeeLMA;
	@ApiModelProperty("上月整月实收保费_健康险")
	private BigDecimal payreffeeLMH;
	@ApiModelProperty("年初到上月底实收保费")
	private BigDecimal payreffeeLMs;
	@ApiModelProperty("年初到上月底实收保费_车险")
	private BigDecimal payreffeeLMsC;
	@ApiModelProperty("年初到上月底实收保费_非车险")
	private BigDecimal payreffeeLMsNc;
	@ApiModelProperty("年初到上月底实收保费_意外险")
	private BigDecimal payreffeeLMsA;
	@ApiModelProperty("年初到上月底实收保费_健康险")
	private BigDecimal payreffeeLMsH;
	@ApiModelProperty("更新时间，增量字段")
	private Date updatedate;
	@ApiModelProperty("备用字段1")
	private String attribute1;
	@ApiModelProperty("备用字段2")
	private String attribute2;
	@ApiModelProperty("备用字段3")
	private String attribute3;
	@ApiModelProperty("备用字段4")
	private String attribute4;
	@ApiModelProperty("备用字段5")
	private String attribute5;
	@ApiModelProperty("备用字段6")
	private String attribute6;

	/**
	 * 设置：主键自增ID
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}
	/**
	 * 获取：主键自增ID
	 */
	public BigDecimal getId() {
		return id;
	}
	/**
	 * 设置：人员工号
	 */
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	/**
	 * 获取：人员工号
	 */
	public String getUsercode() {
		return usercode;
	}
	/**
	 * 设置：实收日期
	 */
	public void setRealpaydate(Date realpaydate) {
		this.realpaydate = realpaydate;
	}
	/**
	 * 获取：实收日期
	 */
	public Date getRealpaydate() {
		return realpaydate;
	}
	/**
	 * 设置：实收保费（昨天实收）（含税）
	 */
	public void setPayreffee(BigDecimal payreffee) {
		this.payreffee = payreffee;
	}
	/**
	 * 获取：实收保费（昨天实收）（含税）
	 */
	public BigDecimal getPayreffee() {
		return payreffee;
	}
	/**
	 * 设置：实收保费-车险
	 */
	public void setPayreffeeCar(BigDecimal payreffeeCar) {
		this.payreffeeCar = payreffeeCar;
	}
	/**
	 * 获取：实收保费-车险
	 */
	public BigDecimal getPayreffeeCar() {
		return payreffeeCar;
	}
	/**
	 * 设置：实收保费-非车险
	 */
	public void setPayreffeeNocar(BigDecimal payreffeeNocar) {
		this.payreffeeNocar = payreffeeNocar;
	}
	/**
	 * 获取：实收保费-非车险
	 */
	public BigDecimal getPayreffeeNocar() {
		return payreffeeNocar;
	}
	/**
	 * 设置：实收保费-意外
	 */
	public void setPayreffeeAcc(BigDecimal payreffeeAcc) {
		this.payreffeeAcc = payreffeeAcc;
	}
	/**
	 * 获取：实收保费-意外
	 */
	public BigDecimal getPayreffeeAcc() {
		return payreffeeAcc;
	}
	/**
	 * 设置：实收保费-健康
	 */
	public void setPayreffeeHea(BigDecimal payreffeeHea) {
		this.payreffeeHea = payreffeeHea;
	}
	/**
	 * 获取：实收保费-健康
	 */
	public BigDecimal getPayreffeeHea() {
		return payreffeeHea;
	}
	/**
	 * 设置：本月累计实收保费（月初到昨天）
	 */
	public void setPayreffeeMS(BigDecimal payreffeeMS) {
		this.payreffeeMS = payreffeeMS;
	}
	/**
	 * 获取：本月累计实收保费（月初到昨天）
	 */
	public BigDecimal getPayreffeeMS() {
		return payreffeeMS;
	}
	/**
	 * 设置：本月累计实收保费-车险
	 */
	public void setPayreffeeMSC(BigDecimal payreffeeMSC) {
		this.payreffeeMSC = payreffeeMSC;
	}
	/**
	 * 获取：本月累计实收保费-车险
	 */
	public BigDecimal getPayreffeeMSC() {
		return payreffeeMSC;
	}
	/**
	 * 设置：本月累计实收保费-非车险
	 */
	public void setPayreffeeMSNc(BigDecimal payreffeeMSNc) {
		this.payreffeeMSNc = payreffeeMSNc;
	}
	/**
	 * 获取：本月累计实收保费-非车险
	 */
	public BigDecimal getPayreffeeMSNc() {
		return payreffeeMSNc;
	}
	/**
	 * 设置：本月累计实收保费-意外
	 */
	public void setPayreffeeMSA(BigDecimal payreffeeMSA) {
		this.payreffeeMSA = payreffeeMSA;
	}
	/**
	 * 获取：本月累计实收保费-意外
	 */
	public BigDecimal getPayreffeeMSA() {
		return payreffeeMSA;
	}
	/**
	 * 设置：本月累计实收保费-健康
	 */
	public void setPayreffeeMSH(BigDecimal payreffeeMSH) {
		this.payreffeeMSH = payreffeeMSH;
	}
	/**
	 * 获取：本月累计实收保费-健康
	 */
	public BigDecimal getPayreffeeMSH() {
		return payreffeeMSH;
	}
	/**
	 * 设置：本年累计实收保费（年初到昨天）
	 */
	public void setPayreffeeYS(BigDecimal payreffeeYS) {
		this.payreffeeYS = payreffeeYS;
	}
	/**
	 * 获取：本年累计实收保费（年初到昨天）
	 */
	public BigDecimal getPayreffeeYS() {
		return payreffeeYS;
	}
	/**
	 * 设置：本年累计实收保费-车险
	 */
	public void setPayreffeeYSC(BigDecimal payreffeeYSC) {
		this.payreffeeYSC = payreffeeYSC;
	}
	/**
	 * 获取：本年累计实收保费-车险
	 */
	public BigDecimal getPayreffeeYSC() {
		return payreffeeYSC;
	}
	/**
	 * 设置：本年累计实收保费-非车险
	 */
	public void setPayreffeeYSNc(BigDecimal payreffeeYSNc) {
		this.payreffeeYSNc = payreffeeYSNc;
	}
	/**
	 * 获取：本年累计实收保费-非车险
	 */
	public BigDecimal getPayreffeeYSNc() {
		return payreffeeYSNc;
	}
	/**
	 * 设置：本年累计实收保费-意外
	 */
	public void setPayreffeeYSA(BigDecimal payreffeeYSA) {
		this.payreffeeYSA = payreffeeYSA;
	}
	/**
	 * 获取：本年累计实收保费-意外
	 */
	public BigDecimal getPayreffeeYSA() {
		return payreffeeYSA;
	}
	/**
	 * 设置：本年累计实收保费-健康
	 */
	public void setPayreffeeYSH(BigDecimal payreffeeYSH) {
		this.payreffeeYSH = payreffeeYSH;
	}
	/**
	 * 获取：本年累计实收保费-健康
	 */
	public BigDecimal getPayreffeeYSH() {
		return payreffeeYSH;
	}
	/**
	 * 设置：上月整月实收保费
	 */
	public void setPayreffeeLM(BigDecimal payreffeeLM) {
		this.payreffeeLM = payreffeeLM;
	}
	/**
	 * 获取：上月整月实收保费
	 */
	public BigDecimal getPayreffeeLM() {
		return payreffeeLM;
	}
	/**
	 * 设置：上月整月实收保费-车险
	 */
	public void setPayreffeeLMC(BigDecimal payreffeeLMC) {
		this.payreffeeLMC = payreffeeLMC;
	}
	/**
	 * 获取：上月整月实收保费-车险
	 */
	public BigDecimal getPayreffeeLMC() {
		return payreffeeLMC;
	}
	/**
	 * 设置：上月整月实收保费_非车险
	 */
	public void setPayreffeeLMNc(BigDecimal payreffeeLMNc) {
		this.payreffeeLMNc = payreffeeLMNc;
	}
	/**
	 * 获取：上月整月实收保费_非车险
	 */
	public BigDecimal getPayreffeeLMNc() {
		return payreffeeLMNc;
	}
	/**
	 * 设置：上月整月实收保费_意外险
	 */
	public void setPayreffeeLMA(BigDecimal payreffeeLMA) {
		this.payreffeeLMA = payreffeeLMA;
	}
	/**
	 * 获取：上月整月实收保费_意外险
	 */
	public BigDecimal getPayreffeeLMA() {
		return payreffeeLMA;
	}
	/**
	 * 设置：上月整月实收保费_健康险
	 */
	public void setPayreffeeLMH(BigDecimal payreffeeLMH) {
		this.payreffeeLMH = payreffeeLMH;
	}
	/**
	 * 获取：上月整月实收保费_健康险
	 */
	public BigDecimal getPayreffeeLMH() {
		return payreffeeLMH;
	}
	/**
	 * 设置：年初到上月底实收保费
	 */
	public void setPayreffeeLMs(BigDecimal payreffeeLMs) {
		this.payreffeeLMs = payreffeeLMs;
	}
	/**
	 * 获取：年初到上月底实收保费
	 */
	public BigDecimal getPayreffeeLMs() {
		return payreffeeLMs;
	}
	/**
	 * 设置：年初到上月底实收保费_车险
	 */
	public void setPayreffeeLMsC(BigDecimal payreffeeLMsC) {
		this.payreffeeLMsC = payreffeeLMsC;
	}
	/**
	 * 获取：年初到上月底实收保费_车险
	 */
	public BigDecimal getPayreffeeLMsC() {
		return payreffeeLMsC;
	}
	/**
	 * 设置：年初到上月底实收保费_非车险
	 */
	public void setPayreffeeLMsNc(BigDecimal payreffeeLMsNc) {
		this.payreffeeLMsNc = payreffeeLMsNc;
	}
	/**
	 * 获取：年初到上月底实收保费_非车险
	 */
	public BigDecimal getPayreffeeLMsNc() {
		return payreffeeLMsNc;
	}
	/**
	 * 设置：年初到上月底实收保费_意外险
	 */
	public void setPayreffeeLMsA(BigDecimal payreffeeLMsA) {
		this.payreffeeLMsA = payreffeeLMsA;
	}
	/**
	 * 获取：年初到上月底实收保费_意外险
	 */
	public BigDecimal getPayreffeeLMsA() {
		return payreffeeLMsA;
	}
	/**
	 * 设置：年初到上月底实收保费_健康险
	 */
	public void setPayreffeeLMsH(BigDecimal payreffeeLMsH) {
		this.payreffeeLMsH = payreffeeLMsH;
	}
	/**
	 * 获取：年初到上月底实收保费_健康险
	 */
	public BigDecimal getPayreffeeLMsH() {
		return payreffeeLMsH;
	}
	/**
	 * 设置：更新时间，增量字段
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	/**
	 * 获取：更新时间，增量字段
	 */
	public Date getUpdatedate() {
		return updatedate;
	}
	/**
	 * 设置：备用字段1
	 */
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	/**
	 * 获取：备用字段1
	 */
	public String getAttribute1() {
		return attribute1;
	}
	/**
	 * 设置：备用字段2
	 */
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	/**
	 * 获取：备用字段2
	 */
	public String getAttribute2() {
		return attribute2;
	}
	/**
	 * 设置：备用字段3
	 */
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	/**
	 * 获取：备用字段3
	 */
	public String getAttribute3() {
		return attribute3;
	}
	/**
	 * 设置：备用字段4
	 */
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	/**
	 * 获取：备用字段4
	 */
	public String getAttribute4() {
		return attribute4;
	}
	/**
	 * 设置：备用字段5
	 */
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	/**
	 * 获取：备用字段5
	 */
	public String getAttribute5() {
		return attribute5;
	}
	/**
	 * 设置：备用字段6
	 */
	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}
	/**
	 * 获取：备用字段6
	 */
	public String getAttribute6() {
		return attribute6;
	}
}
