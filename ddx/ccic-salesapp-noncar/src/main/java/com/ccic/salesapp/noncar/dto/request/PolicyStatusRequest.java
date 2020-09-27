package com.ccic.salesapp.noncar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PolicyStatusRequest {
	/**
	 * 业务号(投保单号/批单号)
	 */
    private String certiNo;

	/**
	 * 业务类型(E：批单,P：保单)
	 */
	private String certiType;

	/**
	 * 保单号
	 */
	private String policyNo;

	/**
	 * 状态(1：已生效 0：未生效)
	 */
	private String status;

	/**
	 * 保单(批单)生效时间(yyyy-MM-dd HH:mm:ss)
	 */
	private String effectiveTime;

	/**
	 * 终保时间(yyyy-MM-dd HH:mm:ss)
	 */
	private String expireTime;

	/**
	 * 保单生成时间(yyyy-MM-dd HH:mm:ss)
	 */
	private String createPolicyTime;
	/**
	 * 收费确认时间(yyyy-MM-dd HH:mm:ss)
	 */
	private String chargeTime;

	/**
	 * 投保确认时间(yyyy-MM-dd HH:mm:ss)
	 */
	private String underWriteEndTime;

	/**
	 * 销售渠道
	 */
	private String salesChannel;

	/**
	 * 支付类型
	 */
	private String payWay;

	/**
	 *支付类型号码
	 */
	private String payWayNo;

	/**
	 * 交强险投保确认码
	 */
	private String validNoBZ;

	/**
	 *商业险投保确认码
	 */
	private String validNoCIP;
}
