package com.example.tlyannoif;

/**
 * @ClassName ConfigConst
 * @Description 全局常量
 */
public class ConfigConst {

	/**
	 * ESB成功
	 */
	public static final int ESB_SUCCESS = 0;

	/**
	 * ESB失败
	 */
	public static final int ESB_SYS_FAILED = 1;

	/**
	 * ESB应用失败
	 */
	public static final int ESB_APP_FAILED = 2;

	/**
	 * 消费方id
	 */
	public static final String CONSUMER_ID = "supsale";

	public static final String CLAUSE_NAME = "条款名称";
	public static final String INSURANCE_LIABILITY = "条款名称";
	public static final String AMOUNT_INSURED = "每人保额（元）";

	/**
	 * 影像类型：CJXS-客户管理，UW_W-健康，UW_Y-货物运输 货运无忧
	 */
	public static final String INSURACNCE_TYPE_CJXS = "CJXS";

	/**
	 * 影像类型：CJXS-客户管理，UW_W-健康，UW_Y-货物运输 货运无忧
	 */
	public static final String INSURACNCE_TYPE_UW_W = "UW_W";

	/**
	 * 影像类型：CJXS-客户管理，UW_W-健康，UW_Y-货物运输 货运无忧
	 */
	public static final String INSURACNCE_TYPE_UW_Y = "UW_Y";

	/**
	 * 操作角色：货运无忧Freight_Transport, 健康healthy,意外ACCIDENT
	 */
	public static final String INSURACNCE_TRANSPORT = "UWNVRole02";
	public static final String INSURACNCE_HEALTHY = "UWNVRole02";
	public static final String INSURACNCE_ACCIDENT = "UWNVRole02";

	public static final String INSURACNCE_TYPE_UW = "UW";

	// 防癌险险种代码
	public static final String INSURACNCE_TYPE_WTI = "WTI";
	// 百万医疗险种代码
	public static final String INSURACNCE_TYPE_WTN = "WTN";
	// 货运无忧险种代码
	public static final String INSURACNCE_TYPE_YEF = "YEF";

	public static final String APPLICATION_IMG_TYPE_NAME = "投保单";
	public static final String APPLICATION_UPLOAD_NODE = "uploadNode";
	public static final Integer APPLICATION_UPLOAD_FILE = 1;

	
	//UW_0
	public static final String APPLICATION_FORM_UW_0 = "UW0004";
	//UW_A
	public static final String APPLICATION_FORM_UW_A = "UWA004";
	//UW_B
	public static final String APPLICATION_FORM_UW_B = "UWB004";
	//UW_C
	public static final String APPLICATION_FORM_UW_C = "UWC004";
	//UW_D
	public static final String APPLICATION_FORM_UW_D = "0002";
	//UW_E
	public static final String APPLICATION_FORM_UW_E = "UWE001";
	//UW_G
	public static final String APPLICATION_FORM_UW_G = "UWG004";
	//UW_H
	public static final String APPLICATION_FORM_UW_H = "UWH004";
	//UW_I
	public static final String APPLICATION_FORM_UW_I = "UWI004";
	//UW_J
	public static final String APPLICATION_FORM_UW_J = "UWJ004";
	//UW_L
	public static final String APPLICATION_FORM_UW_L = "UWL004";
	//UW_M
	public static final String APPLICATION_FORM_UW_M = "UWM004";
	//UW_N
	public static final String APPLICATION_FORM_UW_N = "UWN004";
	//UW_K
	public static final String APPLICATION_FORM_UW_K = "UWK004";
	//UW_O
	public static final String APPLICATION_FORM_UW_O = "UWO004";
	//UW_P
	public static final String APPLICATION_FORM_UW_P = "UWP004";
	//UW_Q
	public static final String APPLICATION_FORM_UW_Q = "UWQ004";
	//UW_S
	public static final String APPLICATION_FORM_UW_S = "UWS004";
	//UW_T
	public static final String APPLICATION_FORM_UW_T = "UWT004";
	//UW_W
	public static final String APPLICATION_FORM_UW_W = "UWW001";
	//UW_X
	public static final String APPLICATION_FORM_UW_X = "UWX004";
	//UW_Y
	public static final String APPLICATION_FORM_UW_Y = "UWY004";
	//UW_Z
	public static final String APPLICATION_FORM_UW_Z = "UWZ004";
	//UW_9
	public static final String APPLICATION_FORM_UW_9 = "UW9001";
	//UW_V
	public static final String APPLICATION_FORM_UW_V = "UWV001";
	//UW_4
	public static final String APPLICATION_FORM_UW_4 = "UW4004";
	
	
	
	public static final String APPLICATION_COMNAME = "大地保险";
	public static final String APPLICATION_OPERATORROLE = "RSXRole002";

	public static final String CSS_HEAD_BODY_PDF = "<style>body{font-family:'SimSun';color:#000000;}@page{size:A4; margin: 0}"
			+ ".tb_table{border: 1px solid;font-size:12px;border-collapse:collapse;padding:0;margin:0;height: 20px;}"
			+ ".div_v1{width:701px;word-break: break-all;margin-left: 36px;line-height:1em;}"
			+ ".sing_image{width:219.47px; height:110.27px;}" 
			+ ".jb_mes{font-weight: bold;}"
			+ ".tb_qm{text-align:right}"
			+ ".tb_qm_t{text-align:right}" 
			+ ".tb_dat{text-align:right}"
			+ ".div_v01{width:701px;word-break: break-all;margin-left: 36px;line-height:1em;padding-top:3px;}"
			+ ".div_v02{width:701px;word-break: break-all;margin-left: 36px;line-height:1em;padding-top:0;}"
			+ ".div_v03{width:701px;word-break: break-all;margin-left: 36px;line-height:1em;padding-top:1px;}"
			+ ".div_v04{width:701px;word-break: break-all;margin-left: 36px;line-height:1em;padding-top:2px;}"
			+ ".div_v2{ width:701px;word-break: break-all;margin-left: 36px;line-height:1em;padding-top:4px;}"
			+ ".div_v05{ width:701px;word-break: break-all;margin-left: 36px;line-height:1em;padding-top:5px;}"
			+ ".icon_image{width:20px;margin-top:-6px}"
			+ ".ins{text-align:left;}"
			+ ".tb_qrs{text-align:center;font-weight: bold;line-height: 1em;letter-spacing: 0.0045em;font-size:2em;color: #000000;}"
			+ ".tb_is{text-align:center}</style></head>";
	public static final String CSS_HEAD = "</head>";
	public static final String CSS_DIV = "(<div.*?)style=\".*";
	public static final String CSS_SPAN = "<span\\s*[^>]*>";
	public static final String CSS_SPAN_FU = "<span style=\"font-size:10.0pt;font-weight:bold;\">";
	public static final String CSS_BR = "<br>";
	public static final String CSS_DIV_FU = "<div>";
	public static final String CSS_BODY = "body";
//	public static final String CSS_STYLE = "style=\"(.*?)\"";
	public static final String CSS_BR_STYLE = "<br(\\s+)?\\/?>";
	public static final String YEAR_MONTH_DAY = "yyyy年MM月dd日";
	public static final String Y_M_D_H_S = "YYYYMMddhhmmss";
	public static final String URL_SRC = "./src/main/resources/";
	public static final String INSURANCE_NOTICE = "TouBaoXuZhi";
	public static final String HEALTH_NOTIFICATION = "JianKangGaoZhi";
	public static final String INSURANCE_DECLARATION = "TouBaoShengMing";
	public static final String MONEY_ZH = "元整（￥";
	public static final String MONEY_YU = "元）";
	public static final String MONEY_WA = "万";
	public static final String TIME = "时";
	public static final String FROM = "自";
	public static final String TO_ZH = "起至";
	public static final String ZHI = "止";
	public static final String COM_CODE="31000000";
}
