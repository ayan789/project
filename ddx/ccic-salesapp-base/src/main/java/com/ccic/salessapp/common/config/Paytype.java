package com.ccic.salessapp.common.config;

/**
 * 支付方式
 *   01:银行卡,02:支票,03:现金，04：在线支付，05：移动POS
 * @author winters
 * @version 1.0
 */
public class Paytype {

    /** 支付方式——网上支付 */
    public static String PAYTYPE_ONLINE = "1"; //

    /** 支付方式——pos支付 */
    public static String PAYTYPE_POS = "2";


    /** 支付方式——无卡支付 */
    public static String NOCARD_PAY = "5";

    /** 支付方式——支票支付 */
    public static String PAYTYPE_CHECK = "3";

    /** 支付方式——现金支付 */
    public static String PAYTYPE_CASH = "4";

    /** 支付方式——微信支付*/
    public static String PAYTYPE_WECHAT = "10";

    /** 支付方式——支付宝支付*/
    public static String PAYTYPE_ALIPAYTI = "99";
    
    
}
    
    
