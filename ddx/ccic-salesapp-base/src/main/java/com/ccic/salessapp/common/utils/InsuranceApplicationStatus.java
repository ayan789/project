package com.ccic.salessapp.common.utils;

/**
 * 投保单状态
 * 
 * @version 1.0
 */
public class InsuranceApplicationStatus {
    
	/** 投保单状态—暂存  */
	public static final String ZANCUN = "0";
	/** 投保单状态—待核保*/
	public static final String DAIHEBAO = "1";
	/** 投保单状态—核保中 */
	public static final String HEBAOZHONG = "9";
	/** 投保单状态—已核保*/
	public static final String YIHEBAO = "2";
	/** 投保单状态—核保失败*/
	public static final String HEBAOSHIBAI = "3";
	/** 投保单状态—待支付*/
	public static final String DAIZHIFU = "4";
	/** 投保单状态—支付中*/
	public static final String ZHIFUZHONG = "5";
	/** 投保单状态—已支付*/
	public static final String YIZHIFU = "6";
	/** 投保单状态—生效*/
	public static final String SHENGXIAO = "7";
	/** 投保单状态—作废 */
	public static final String ZUOFEI = "8";

	
}

