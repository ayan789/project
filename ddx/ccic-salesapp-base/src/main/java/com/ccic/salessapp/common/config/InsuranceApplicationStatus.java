package com.ccic.salessapp.common.config;

/**
 * 投保单状态
 *
 * @version 1.0
 */
public interface InsuranceApplicationStatus {
    /**
     * 投保单状态—暂存
     */
    String ZANCUN = "10";
    /**
     * 投保单状态—待提交
     */
    String DAIHEBAO = "20";
    /**
     * 投保单状态—核保中
     */
    String HEBAOZHONG = "21";
    /**
     * 投保单状态—核保失败
     */
    String HEBAOSHIBAI = "22";
    /**
     * 投保单状态—已核保/核保通过
     */
    String YIHEBAO = "23";
    /**
     * 投保单状态—核保退回
     */
    String HEBAOTH = "24";
    /**
     * 投保单状态—已生效
     */
    String SHENGXIAO = "30";
    /**
     * 投保单状态—失效
     */
    String ZUOFEI = "31";
    /**
     * 投保单状态—满期
     */
    String MANQI = "32";
    /**
     * 投保单状态—待支付
     */
    String DAIZHIFU = "40";
    /**
     * 投保单状态—支付中
     */
    String ZHIFUZHONG = "41";
    /**
     * 投保单状态—已支付(支付成功)
     */
    String YIZHIFU = "42";
    /**
     * 投保单状态—支付失败
     */
    String ZHIFUSHIBAI = "43";
    /**
     * 已过期
     */
    String GUOQI = "50";
    /**
     * 待续保
     */
    String DAIXUBAO = "51";
    /**
     * 已批单
     */
    String YIPIDAN = "6";
}
