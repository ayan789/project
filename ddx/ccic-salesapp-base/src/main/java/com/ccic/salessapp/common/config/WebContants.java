package com.ccic.salessapp.common.config;

import java.util.HashMap;
import java.util.Map;



public class WebContants {
	/**
	 * COMPY_CODE公司代号
	 */
	public final static String COMPY_CODE="P1025";

	public final static String C_FTP_IP = "10.1.13.150";//图片上传ftp地址
	public final static int C_FTP_Port = 21;//图片上传ftp端口
	
	/**
	 * 消费方ID
	 */
	public final static String CONSUMER_ID_MOBILE="mobile";
	public final static String CONSUMER_ID_IOPEN="iopen";
	public final static String CONSUMER_ID_ICORE="icore";
	
	/**
	 * 提醒类型
	 */
	public final static String C_REMIND_BIRTHDAY="1010";//生日提醒
	public final static String C_REMIND_CLAIM="1020";//理赔提醒
	public final static String C_REMIND_LICENSE="1030";//行驶证、驾驶证到期提醒
	public final static String C_REMIND_SCHEDULE="1040";//日程提醒
	public final static String C_REMIND_CONTINUE="1050";//提醒续保提醒

	/**
	 * 获取未读信息状态
	 */
	public final static long L_UNREAD_STATUS = 0;//提醒-未读
	public final static long L_READ_STATUS = 1;//提醒-已读

	/**
	 * 保险大类
	 */
	public final static String C_INSURANCE_TYPE_AUTO="1";//车险
	public final static String C_INSURANCE_TYPE_UN_AUTO="2";//非车险
	public final static String C_INSURANCE_TYPE_LIFE="3";//人身险

	/**
	 * 客户类型
	 */
	public final static String C_CUSTOMER_TYPE_PERSON="10";//个人
	public final static String C_CUSTOMER_TYPE_BUSINESS="20";//企业

	/**
	 * 续保跟踪状态
	 */
	public final static String C_TRACKS_TATUS_FINISH="1";//已跟踪
	public final static String C_TRACKS_TATUS_FINISH_UN="0";//未跟踪

	/**
	 * 订单来源
	 */
	public final static String C_ORDER_SOURCE_MOBILE="M";// 移动销售支持
	public final static String C_ORDER_SOURCE_WEB="S";//WEB销售支持

	/**
	 * 投保类型
	 */
	public final static String C_INSURANCE_TYPE_FROCE="1";//交强险
	public final static String C_INSURANCE_TYPE_BUSINESS="2";//商业险
	public final static String C_INSURANCE_TYPE_TWO="3";//交、商双险
	public final static String C_INSURANCE_TYPE_PROPERTY="4";//财意险
	
	/**
	 *保险险别
	 */
	public final static String C_INSURANCE_Kind_FROCE="BZ";//交强险
	public final static String C_INSURANCE_Kind_LOSS="A";//车辆损失险
	public final static String C_INSURANCE_Kind_THIRD="B";//第三者责任险
	public final static String C_INSURANCE_Kind_DRIVER="D3";//驾驶员车上人员责任险
	public final static String C_INSURANCE_Kind_PASSENGER="D4";//乘客车上人员责任险
	public final static String C_INSURANCE_Kind_STEAL="G1";//全车盗抢险
	public final static String C_INSURANCE_Kind_GLASS="F";//玻璃单独破碎险
	public final static String C_INSURANCE_Kind_SCRATCH="L";//车身划痕险
	public final static String C_INSURANCE_Kind_AUTOIGNITION="Z";//自燃损失险
	public final static String C_INSURANCE_Kind_REPAIR="A4";//选择汽车专修厂特约
	public final static String C_INSURANCE_Kind_SPECIAL="M";//不计免赔特约险
	public final static String C_INSURANCE_Kind_X="X";//新增设备损失险
	public final static String C_INSURANCE_Kind_A6="A6";//无法找到第三方特约险
	public final static String C_INSURANCE_Kind_B11="B11";//三者险法定节假日限额翻倍险
	public final static String C_INSURANCE_Kind_A7="A7";//机动车损失保险（IACJQL0001）
	public final static String C_INSURANCE_Kind_A71="A71";//附加车轮单独损坏除外特约险（IACJQL0201）
	public final static String C_INSURANCE_Kind_A72="A72";//新增设备损失险（IACJQL0001）


	/**
	 *新核心保险险别
	 */
	public final static String N_INSURANCE_Kind_FROCE="C100120";//交强险
	public final static String N_INSURANCE_Kind_LOSS="C100121";//车辆损失险
	public final static String N_INSURANCE_Kind_THIRD="C100122";//第三者责任险
	public final static String N_INSURANCE_Kind_DRIVER="C100123";//驾驶员车上人员责任险
	public final static String N_INSURANCE_Kind_PASSENGER="C100124";//乘客车上人员责任险
	public final static String N_INSURANCE_Kind_STEAL="C100125";//全车盗抢险
	public final static String N_INSURANCE_Kind_GLASS="C100126";//玻璃单独破碎险
	public final static String N_INSURANCE_Kind_SCRATCH="C100129";//车身划痕险
	public final static String N_INSURANCE_Kind_AUTOIGNITION="C100127";//自燃损失险
	public final static String N_INSURANCE_Kind_REPAIR="C100135";//选择汽车专修厂特约
	public final static String N_INSURANCE_Kind_X="C100128";//新增设备损失险
	public final static String N_INSURANCE_Kind_A6="C100134";//无法找到第三方特约险
	public final static String N_INSURANCE_Kind_B11="C101017";//三者险法定节假日限额翻倍险
	public final static String N_INSURANCE_Kind_A7="C101011";//机动车损失保险（IACJQL0001）
	public final static String N_INSURANCE_Kind_A71="C101012";//附加车轮单独损坏除外特约险（IACJQL0201）
	public final static String N_INSURANCE_Kind_A72="C101015";//新增设备损失险

	/**
	 *新核心非常用商业险
	 */
	public final static String N_INSURANCE_KIND_X1="C100130";//发动机涉水损失险
	public final static String N_INSURANCE_KIND_T="C100131";//修理期间费用补偿险
	public final static String N_INSURANCE_KIND_K1="C100148";//起重、装卸、挖掘车辆损失扩展条款
	public final static String N_INSURANCE_KIND_K2="C100149";//特种车辆固定设备、仪器损坏扩展条款
	public final static String N_INSURANCE_KIND_R="C100133";//精神损害抚慰金损失险
	public final static String N_INSURANCE_KIND_D2="C100132";//车上货物责任险

	/**
	 *非常用商业险
	 */
	public final static String C_INSURANCE_KIND_X1="X1";//发动机涉水损失险
	public final static String C_INSURANCE_KIND_T="T";//修理期间费用补偿险
	public final static String C_INSURANCE_KIND_K1="K1";//起重、装卸、挖掘车辆损失扩展条款
	public final static String C_INSURANCE_KIND_K2="K2";//特种车辆固定设备、仪器损坏扩展条款
	public final static String C_INSURANCE_KIND_R="R";//精神损害抚慰金损失险
	public final static String C_INSURANCE_KIND_D2="D2";//车上货物责任险

	/**
	 * 险别简称集合(kindCode-kindName)
	 * */
	public static Map<String, String> kindMap = new HashMap<String, String>();
	static{
		kindMap.put(WebContants.C_INSURANCE_Kind_FROCE, "交强险");
		kindMap.put(WebContants.C_INSURANCE_Kind_LOSS, "车损险");
		kindMap.put(WebContants.C_INSURANCE_Kind_THIRD, "三者险");
		kindMap.put(WebContants.C_INSURANCE_Kind_STEAL, "盗抢险");
		kindMap.put(WebContants.N_INSURANCE_Kind_FROCE, "交强险");
		kindMap.put(WebContants.N_INSURANCE_Kind_LOSS, "车损险");
		kindMap.put(WebContants.N_INSURANCE_Kind_THIRD, "三者险");
		kindMap.put(WebContants.N_INSURANCE_Kind_STEAL, "盗抢险");
	}


	/**
	 * 商业险短期费率计算方式
	 */
	public final static String C_BUSINESS_COMPUTE_TYPE_YEAR="1";//年
	public final static String C_BUSINESS_COMPUTE_TYPE_MONTH="2";//月
	public final static String C_BUSINESS_COMPUTE_TYPE_DAY="3";//日

	/**
	 * 底线折扣类型
	 */
	public  final static String C_BUSINESS_DISTYPE1="1";//行业自律
	public  final static String C_BUSINESS_DISTYPE2="2";//公司政策
	public  final static String C_BUSINESS_DISTYPE3="3";//规则引擎
	/**
	 * 业务来源
	 */
	public final static String C_BUSINESS_SOURCE_SALESMAN="0101";//业务员
	public final static String C_BUSINESS_SOURCE_SALESSTORE="0201";//门店直销

	/**
	 * 业务来源属性
	 */
	public final static String C_BUSINESS_SOURCE_ATTRIBUTE="0801";//销售支持系统
	public final static String C_BUSINESS_SOURCE_ATTRIBUTE_MPPLAT="I00060";//销售支持系统
	/**
	 * 关系人性质
	 */
	public final static String C_INSURANCE_RELATIONSHIP_HOST="001000";//车主
	public final static String C_INSURANCE_RELATIONSHIP_DRIVER="000100";//约定驾驶员
	public final static String C_INSURANCE_RELATIONSHIP_POLICYHOLDER="100000";//投保人
	public final static String C_INSURANCE_RELATIONSHIP_INSURED="010000";//被保人
	public final static String C_INSURANCE_RELATIONSHIP_BUSINESS_OPERATOR="200000";//企业经办人
	/**
	 * 新核心关系人性质
	 */
	public final static String N_INSURANCE_RELATIONSHIP_HOST="3";//车主
	public final static String N_INSURANCE_RELATIONSHIP_POLICYHOLDER="1";//投保人
	public final static String N_INSURANCE_RELATIONSHIP_INSURED="2";//被保人
	public final static String N_INSURANCE_RELATIONSHIP_BUSINESS_OPERATOR="5";//企业经办人
	/**
	 * 证件类型
	 */
	public final static String C_IDENTIFY_TYPE="05";//证件类型 05驾驶证
	public final static String C_IDENTIFY_TYPE_IDCARD="01";//身份证
	/**
	 * 被保险人性质
	 */
	public final static String C_INSURER_NATURE_PERSON="3";//自然人/或者是16个9
	public final static String C_INSURER_NATURE_DPT="4";//法人或组织
	/**
	 * 访问ecif接口角色
	 */
	public final static String C_ECIF_ROLE="1001";//客户保单查询role 个人/企业客户详细信息role
	/**
	 * 缴税标志
	 */
	public final static String C_INSURANCE_CARTAX_PAY="0";//缴税
	public final static String C_INSURANCE_CARTAX_MIN="1";//减税
	public final static String C_INSURANCE_CARTAX_OVER="2";//已完税
	public final static String C_INSURANCE_CARTAX_FREE="3";//免税
	public final static String C_INSURANCE_CARTAX_REFUSE="4";//拒缴
	public final static String C_INSURANCE_CARTAX_UNSTART="5";//未开征车船税
	public final static String C_INSURANCE_CARTAX_OWNING="6";//欠税
	public final static String C_INSURANCE_CARTAX_PAYANDBACK="7";//补税并缴税
	public final static String C_INSURANCE_CARTAX_DELAY="8";//缓缴
	public final static String C_INSURANCE_CARTAX_NOTCOMPANY="9";//非保险公司代缴
	public final static String C_INSURANCE_CARTAX_DISPUTED="10";//有争议
	/**
	 * 新核心缴税标志
	 */
	public final static String N_INSURANCE_CARTAX_PAY="T";//缴税
	public final static String N_INSURANCE_CARTAX_MIN="C";//减税
	public final static String N_INSURANCE_CARTAX_OVER="P";//已完税
	public final static String N_INSURANCE_CARTAX_FREE="E";//免税
	public final static String N_INSURANCE_CARTAX_REFUSE="R";//拒缴
	/**
	 * 特殊机构代码
	 */
	public final static String C_COMCODE_BEIJING="1101";//北京分公司
	public final static String C_COMCODE_TIANJIN="1201";//天津分公司
	public final static String C_COMCODE_SHANGHAI="3101";//上海分公司
	public final static String C_COMCODE_SHENZHEN="4403";//深圳分公司
	public final static String C_COMCODE_GUANGDONG="4401";//广东分公司
	public final static String C_COMCODE_NINGBO="3302";//宁波分公司
	public final static String C_COMCODE_MOCO="3502";//厦门分公司
	public final static String C_COMCODE_SICHUAN="5101";//四川分公司
	public final static String C_COMCODE_JIANGSU="3201";//江苏分公司
	public final static String C_COMCODE_GUIZHOU="5201";//贵州分公司

	public final static String C_COMCODE_HEBEI="1301";//河北分公司
	public final static String C_COMCODE_SHANXI_01="1401";//山西分公司
	public final static String C_COMCODE_NEIMENGGU="1501";//内蒙古分公司
	public final static String C_COMCODE_LIAONING="2101";//辽宁分公司
	public final static String C_COMCODE_DALIAN="2101";//大连分公司
	public final static String C_COMCODE_JILIN="2201";//吉林分公司
	public final static String C_COMCODE_HELONGJINAG="2301";//黑龙江分公司
	public final static String C_COMCODE_ZHEJIANG="3301";//浙江分公司
	public final static String C_COMCODE_ANHUI="3401";//安徽分公司
	public final static String C_COMCODE_FUJIAN="3501";//福建分公司
	public final static String C_COMCODE_JIANGXI="3601";//江西分公司
	public final static String C_COMCODE_SHANDONG="3701";//山东分公司
	public final static String C_COMCODE_QINGDAO="3702";//青岛分公司
	public final static String C_COMCODE_HENAN="4101";//河南分公司
	public final static String C_COMCODE_HUBEI="4201";//湖北分公司
	public final static String C_COMCODE_HUNAN="4301";//湖南分公司
	public final static String C_COMCODE_GUANGXI="4501";//广西分公司
	public final static String C_COMCODE_HAINAN="4601";//海南分公司
	public final static String C_COMCODE_CHONGQING="5001";//重庆分公司
	public final static String C_COMCODE_YUNNAN="5301";//云南分公司
	public final static String C_COMCODE_XIZANG="5401";//西藏分公司
	public final static String C_COMCODE_SHANXI_2="6101";//陕西分公司
	public final static String C_COMCODE_NINGXIA="6401";//宁夏分公司
	public final static String C_COMCODE_GANSU="6201";//甘肃分公司
	public final static String C_COMCODE_QINGHAI="6301";//青海分公司
	public final static String C_COMCODE_XINJIANG="6501";//新疆分公司

	/*
	 * 车辆使用性质
	 */
	public final static String C_CARUSENATURE_SELVES="85";//家庭自用
	public final static String C_CARUSENATURE_NOTSEL="84";//非家庭自用
	public final static String C_CARUSENATURE_BUSINESS="94";//营业
	public final static String C_CARUSENATURE_LEASE="82";//出租租赁
	public final static String C_CARUSENATURE_ROAD="87";//公路客运
	public final static String C_CARUSENATURE_BUS="89";//公交客运
	public final static String C_CARUSENATURE_BUSINESS_TRANSPORT="91";//公交客运
	public final static String C_CARUSENATURE_YY_LEASE="95";//预约出租客运
	/*
	 * 新核心车辆使用性质
	 */
	public final static String N_CARUSENATURE_SELVES="85";//家庭自用
	public final static String N_CARUSENATURE_YY_LEASE="95";//预约出租客运
	public final static String N_CARUSENATURE_BUSINESS="100";//营业
	public final static String N_CARUSENATURE_LEASE="101";//出租租赁
	public final static String N_CARUSENATURE_BUS="102";//公交客运
	public final static String N_CARUSENATURE_ROAD="103";//公路客运
	public final static String N_CARUSENATURE_BUSINESS_TRANSPORT="104";//营业性货运
	public final static String N_CARUSENATURE_BUSINESS_TRIP="105";//营业旅游客运
	public final static String N_CARUSENATURE_BUSINESS_GUACHE="106";//营业挂车
	public final static String N_CARUSENATURE_KECHE_TEZHONGCHE="200";//非营业
	public final static String N_CARUSENATURE_KECHE_QIYE="220";//非营业企业
	public final static String N_CARUSENATURE_KECHE_JIGUAN="230";//非营业机关
	public final static String N_CARUSENATURE_HUOCHE="240";//非营业货运
	public final static String N_CARUSENATURE_GUACHE="250";//非营业挂车
	/*
	 * 车辆种类
	 */
	public final static String C_CARKIND_G0="G0";//挂车
	public final static String C_CARKIND_H0="H0";//货车 北京
	public final static String C_CARKIND_I0="I0";//货车 上海  低速货车
	public static final String C_CARKIND_TT = "TT";//集装箱拖车
	public static final String C_CARKIND_I1 = "I1";//三轮汽车
	public final static String C_CARKIND_A0="A0";//客车
	/*
	 * 新核心车辆种类
	 */
	public final static String N_CARKIND_G0="31";//挂车
	public final static String N_CARKIND_H0="21";//货车 北京
	public final static String N_CARKIND_I0="22";//货车 上海  低速货车
	//	public static final String N_CARKIND_TT = "TT";//集装箱拖车
	public static final String N_CARKIND_I1 = "I1";//三轮汽车
	public final static String N_CARKIND_A0="11";//客车
	/**
	 * 投被保人与车辆关系
	 */
	public final static String PEOPLE_CAR_RELATION_OWN="1";//所有
	public final static String PEOPLE_CAR_RELATION_USE="2";//使用
	public final static String PEOPLE_CAR_RELATION_MANAGE="3";//管理
	
	
	/*
	 * 正则表达式
	 */
	public final static String REGEX_PLUS_FLOAT="^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";//正浮点数
	public final static String REGEX_CHINESE_LETTER_MIN2="^[\u4e00-\u9fa5a-zA-Z]{2}[\u4e00-\u9fa5a-zA-Z]*$";//中文或字母，至少两个字符
	public final static String REGEX_MOBILE="^1\\d{10}$";//手机号，11位数字，以1开头
	public final static String REGEX_MOBILE2="^1(3|4|5|7|8)\\d{9}$";//手机号，11位数字，以1开头 13、14、15、17、18
	public final static String REGEX_FOREIGNER_IDENTIFYNUMBER= "^[0-9A-Za-z]{15}$"; //15位数字或字母正则表达式
	/**
	 * 产品代码
	 */
	public final static String PRODUCT_BUSINESS_DEA="DEA";//机动车商业保险
	public final static String PRODUCT_TRAFFIC_DFA="DFA";//机动车强制保险
	public final static String PRODUCT_SPECIAL_CAR_BUSINESS_DEB="DEB";//特种车商业保险
	public final static String PRODUCT_SPECIAL_CAR_TRAFFIC_DFB="DFB";//特种车强制保险
	public final static String PRODUCT_JOINT_EXD="EXD";//驾乘无忧险
	public final static String PRODUCT_JOINT_EFW="EFW";//驾行无忧险
	/**
	 * 证件类型码值映射
	 * **/
	public final static String[] IndetifiType =
			{"111-01",
					"113-02",
					"414-03",
					"114-04",
					"553-27",
					"801-07",
					"994-19",
					"810-21",
					"28-28"
			};

	public static Map<String, String> indetifiTypeMap = new HashMap<String, String>();//证件类型码值映射
	static{
		indetifiTypeMap.put("111", "01");
		indetifiTypeMap.put("113", "02");
		indetifiTypeMap.put("414", "03");
		indetifiTypeMap.put("114", "04");
		indetifiTypeMap.put("553", "27");
		indetifiTypeMap.put("801", "07");
		indetifiTypeMap.put("994", "19");
		indetifiTypeMap.put("810", "21");
		indetifiTypeMap.put("28", "28");
	}

	public static Map<String, String> insuranceCompanyMap = new HashMap<String, String>();//保险公司代码映射
	static{
		insuranceCompanyMap.put("AAIC", "安信农业保险股份有限公司");
		insuranceCompanyMap.put("ABIC", "安邦财产保险股份有限公司");
		insuranceCompanyMap.put("ACIC", "安诚财产保险股份有限公司");
		insuranceCompanyMap.put("AHIC", "安华农业保险股份有限公司");
		insuranceCompanyMap.put("AICS", "永诚财产保险股份有限公司");
		insuranceCompanyMap.put("BOCI", "中银保险有限公司");
		insuranceCompanyMap.put("BPIC", "渤海财产保险股份有限公司");
		insuranceCompanyMap.put("CAIC", "长安责任保险股份有限公司");
		insuranceCompanyMap.put("CCIC", "中国大地财产保险股份有限公司");
		insuranceCompanyMap.put("CICP", "中华联合财产保险股份有限公司");
		insuranceCompanyMap.put("CPIC", "中国太平洋财产保险股份有限公司");
		insuranceCompanyMap.put("DBIC", "都邦财产保险股份有限公司");
		insuranceCompanyMap.put("DICC", "史带财产保险股份有限公司");
		insuranceCompanyMap.put("GPIC", "中国人寿财产保险股份有限公司");
		insuranceCompanyMap.put("HAIC", "华安财产保险股份有限公司");
		insuranceCompanyMap.put("HNIC", "华农财产保险股份有限公司");
		insuranceCompanyMap.put("HTIC", "华泰财产保险有限公司");
		insuranceCompanyMap.put("MACN", "亚太财产保险有限公司");
		insuranceCompanyMap.put("PAIC", "中国平安财产保险股份有限公司");
		insuranceCompanyMap.put("PICC", "中国人民财产保险股份有限公司");
		insuranceCompanyMap.put("SMIC", "阳光农业相互保险公司");
		insuranceCompanyMap.put("TAIC", "天安财产保险股份有限公司");
		insuranceCompanyMap.put("TPBX", "安盛天平财产保险股份有限公司");
		insuranceCompanyMap.put("TPIC", "太平财产保险有限公司");
		insuranceCompanyMap.put("YAIC", "永安财产保险股份有限公司");
		insuranceCompanyMap.put("YGBX", "阳光财产保险股份有限公司");
		insuranceCompanyMap.put("ZSIC", "浙商财产保险股份有限公司");
		insuranceCompanyMap.put("ZKIC", "紫金财产保险股份有限公司");
		insuranceCompanyMap.put("JTIC", "锦泰财产保险股份有限公司");
		insuranceCompanyMap.put("AIGC", "美亚财产保险有限公司");
		insuranceCompanyMap.put("AMIC", "中航安盟财产保险有限公司");
		insuranceCompanyMap.put("AZCN", "安联财产保险（中国）有限公司");
		insuranceCompanyMap.put("CATH", "国泰财产保险有限责任公司");
		insuranceCompanyMap.put("CHAC", "诚泰财产保险股份有限公司");
		insuranceCompanyMap.put("CJCX", "长江财产保险股份有限公司");
		insuranceCompanyMap.put("DHIC", "鼎和财产保险股份有限公司");
		insuranceCompanyMap.put("FPIC", "富邦财产保险有限公司");
		insuranceCompanyMap.put("GYIC", "国元农业保险股份有限公司");
		insuranceCompanyMap.put("LIHI", "利宝保险有限公司");
		insuranceCompanyMap.put("SPIC", "三星财产保险（中国）有限公司");
		insuranceCompanyMap.put("TMNF", "东京海上日动火灾保险（中国）有限公司");
		insuranceCompanyMap.put("TSBX", "泰山财产保险股份有限公司");
		insuranceCompanyMap.put("UTIC", "众诚汽车保险股份有限公司");
		insuranceCompanyMap.put("XDCX", "信达财产保险股份有限公司");
		insuranceCompanyMap.put("YDCX", "英大泰和财产保险股份有限公司");
		insuranceCompanyMap.put("ZMBX", "中煤财产保险股份有限公司");
		insuranceCompanyMap.put("ZYIC", "中意财产保险有限公司");
		insuranceCompanyMap.put("CRIC", "富德财产保险股份有限公司");
		insuranceCompanyMap.put("SJIC", "日本财产保险（中国）有限公司");
		insuranceCompanyMap.put("XAIC", "鑫安汽车保险股份有限公司");
		insuranceCompanyMap.put("HYIC", "现代财产保险（中国）有限公司");
		insuranceCompanyMap.put("BGIC", "北部湾财产保险股份有限公司");
		insuranceCompanyMap.put("MSIC", "三井住友海上火灾保险（中国）有限公司");
		insuranceCompanyMap.put("HBIC", "恒邦财产保险股份有限公司");
		insuranceCompanyMap.put("HHBX", "华海财产保险股份有限公司");
		insuranceCompanyMap.put("YZIC", "燕赵财产保险股份有限公司");
		insuranceCompanyMap.put("ZYBX", "中原农业保险股份有限公司");
		insuranceCompanyMap.put("ZLIC", "中路财产保险股份有限公司");
		insuranceCompanyMap.put("JLIC", "久隆财产保险有限公司");
		insuranceCompanyMap.put("QHIC", "新疆前海联合财产保险股份有限公司");
		insuranceCompanyMap.put("ZFIC", "珠峰财产保险股份有限公司");
		insuranceCompanyMap.put("HGIC", "海峡金桥财产保险股份有限公司");
		insuranceCompanyMap.put("APIC", "安心财产保险有限责任公司");
		insuranceCompanyMap.put("ULIC", "合众财产保险股份有限公司");
		insuranceCompanyMap.put("CRCI", "中国铁路财产保险自保有限公司");
		insuranceCompanyMap.put("ZAPA", "众安在线财产保险股份有限公司");
		insuranceCompanyMap.put("TKIC", "泰康在线财产保险股份有限公司");
		insuranceCompanyMap.put("CCBP", "建信财产保险有限公司");
		insuranceCompanyMap.put("EAIC", "易安财产保险股份有限公司");
		insuranceCompanyMap.put("YPIC", "黄河财产保险股份有限公司");
		insuranceCompanyMap.put("MPIC", "融盛财产保险股份有限公司");
	}

	public static Map<String, String> coverageMap = new HashMap<String, String>();//险种名称映射
	static{
		coverageMap.put("100", "机动车强制责任保险");
		coverageMap.put("200", "车损险");
		coverageMap.put("201", "非常损失险");
		coverageMap.put("210", "车身划痕损失险条款");
		coverageMap.put("211", "车身油漆单独损伤险条款");
		coverageMap.put("220", "火灾、爆炸、自燃损失险条款");
		coverageMap.put("221", "营业用汽车损失保险附加火灾、爆炸损失险");
		coverageMap.put("231", "玻璃单独破碎险条款");
		coverageMap.put("232", "车轮单独损坏险条款");
		coverageMap.put("233", "车身、倒车镜单独损坏保险条款");
		coverageMap.put("240", "多次事故免赔率特约条款");
		coverageMap.put("241", "家庭自用汽车多次出险增加免赔率特约条款");
		coverageMap.put("242", "营业用汽车损失保险附加多次事故免赔补偿险");
		coverageMap.put("243", "意外事故污染责任险");
		coverageMap.put("251", "选择修理厂特约条款");
		coverageMap.put("252", "指定修理厂特约条款");
		coverageMap.put("260", "新增加设备损失保险条款");
		coverageMap.put("270", "机动车停驶损失险条款");
		coverageMap.put("280", "附加换件特约条款");
		coverageMap.put("290", "涉水损失险条款");
		coverageMap.put("291", "发动机特别损失险条款");
		coverageMap.put("310", "自燃损失险条款");
		coverageMap.put("320", "附加恐怖活动、群体性暴力事件车辆损失险");
		coverageMap.put("330", "可选免赔额特约条款");
		coverageMap.put("350", "起重、装卸、挖掘车辆损失扩展条款");
		coverageMap.put("360", "特种车辆固定设备、仪器损坏扩展条款");
		coverageMap.put("361", "特种加费");
		coverageMap.put("370", "约定区域通行费用特约条款");
		coverageMap.put("371", "东海大桥通行安全特约费用保险条款");
		coverageMap.put("380", "附加特约服务险");
		coverageMap.put("381", "送油、充电服务特约条款");
		coverageMap.put("382", "拖车服务特约条款");
		coverageMap.put("383", "送水、更换保险丝特约服务保险条款");
		coverageMap.put("384", "更换轮胎服务特约条款");
		coverageMap.put("385", "代步机动车服务特约条款");
		coverageMap.put("386", "停驶租车补偿险");
		coverageMap.put("390", "救援费用特约条款");
		coverageMap.put("410", "随车行李物品损失保险");
		coverageMap.put("411", "车上运动器具失窃险");
		coverageMap.put("412", "全车盗抢附加高尔夫球具盗窃险");
		coverageMap.put("420", "新车特约条款");
		coverageMap.put("421", "新车特约条款A");
		coverageMap.put("422", "新车特约条款B");
		coverageMap.put("500", "盗抢险");
		coverageMap.put("510", "租车人人车失踪险条款");
		coverageMap.put("520", "零部件、附属设备被盗窃险条款");
		coverageMap.put("600", "三者险");
		coverageMap.put("610", "附加保险事故善后处理费用险");
		coverageMap.put("611", "法律费用特约条款");
		coverageMap.put("620", "事故附随费用特约条款");
		coverageMap.put("621", "异地出险住宿费特约条款");
		coverageMap.put("630", "附加油污污染责任保险");
		coverageMap.put("640", "附加交通事故精神损害赔偿责任保险");
		coverageMap.put("700", "车上人员责任险");
		coverageMap.put("701", "车上人员责任险（司机）");
		coverageMap.put("702", "车上人员责任险（乘客）");
		coverageMap.put("703", "无过失责任险");
		coverageMap.put("710", "使用安全带特约条款");
		coverageMap.put("800", "车上货物责任险");
		coverageMap.put("801", "车载货物碰撞险");
		coverageMap.put("802", "车上货物掉落责任险条款");
		coverageMap.put("900", "不计免赔率特约条款");
		coverageMap.put("901", "绝对免赔额特约条款");
		coverageMap.put("910", "基本险不计免赔特约条款");
		coverageMap.put("911", "不计免赔率（车损险）");
		coverageMap.put("912", "不计免赔率（三者险）");
		coverageMap.put("920", "附加险不计免赔特约条款");
		coverageMap.put("921", "不计免赔率（机动车盗抢险）");
		coverageMap.put("922", "不计免赔率（车身划痕损失险）");
		coverageMap.put("923", "不计免赔率（新增加设备损失保险）");
		coverageMap.put("924", "不计免赔率（发动机特别损失险）");
		coverageMap.put("925", "不计免赔率（车上货物责任险）");
		coverageMap.put("926", "不计免赔率（附加油污污染责任险）");
		coverageMap.put("927", "不计免赔率（车上人员责任险）");
		coverageMap.put("928", "不计免赔率（车上人员责任险（司机））");
		coverageMap.put("929", "不计免赔率（车上人员责任险（乘客））");
		coverageMap.put("940", "教练车特约条款");
		coverageMap.put("941", "教练车特约条款（车损险）");
		coverageMap.put("942", "教练车特约条款（三者险）");
		coverageMap.put("943", "教练车特约条款（车上人员责任险）");
		coverageMap.put("950", "附加机动车出境保险");
		coverageMap.put("951", "附加机动车出境保险（车损险）");
		coverageMap.put("952", "附加机动车出境保险（三者险）");
		coverageMap.put("960", "节假日行驶区域扩展特约条款");
		coverageMap.put("961", "出省加费");
		coverageMap.put("0101200", "机动车损失保险");
		coverageMap.put("0101231", "玻璃单独破碎险");
		coverageMap.put("0101310", "自燃损失险");
		coverageMap.put("0101260", "新增加设备损失险");
		coverageMap.put("0101210", "车身划痕损失险");
		coverageMap.put("0101290", "发动机涉水损失险");
		coverageMap.put("0101230", "修理期间费用补偿险");
		coverageMap.put("0101250", "机动车损失保险无法找到第三方特约险");
		coverageMap.put("0101252", "指定修理厂险");
		coverageMap.put("0101600", "机动车第三者责任保险");
		coverageMap.put("0101800", "车上货物责任险");
		coverageMap.put("0101610", "精神损害抚慰金责任险");
		coverageMap.put("0101701", "机动车车上人员责任保险（司机）");
		coverageMap.put("0101702", "机动车车上人员责任保险（乘客）");
		coverageMap.put("0101500", "机动车全车盗窃保险");
		coverageMap.put("0101911", "不计免赔率险（机动车损失保险）");
		coverageMap.put("0101912", "不计免赔率险（机动车第三者责任保险）");
		coverageMap.put("0101928", "不计免赔率险（机动车车上人员责任保险（司机））");
		coverageMap.put("0101929", "不计免赔率险（机动车车上人员责任保险（乘客））");
		coverageMap.put("0101921", "不计免赔率险（机动车全车盗抢保险）");
		coverageMap.put("0101970", "不计免赔率险（自燃损失险）");
		coverageMap.put("0101971", "不计免赔率险（车身划痕损失险）");
		coverageMap.put("0101972", "不计免赔率险（发动机涉水损失险）");
		coverageMap.put("0101925", "不计免赔率险（车上货物责任险）");
		coverageMap.put("0101973", "不计免赔率险（精神损害抚慰金责任险）");
		coverageMap.put("0101923", "不计免赔率险（新增加设备损失险）");
		coverageMap.put("0101004", "机动车第三者责任保险法定节假日限额翻倍险");
		coverageMap.put("0201200", "机动车损失保险（IACJQL0001）");
		coverageMap.put("0201001", "附加绝对免赔率特约险（IACJQL0101）");
		coverageMap.put("0201002", "附加车轮单独损坏除外特约险（IACJQL0201");
		coverageMap.put("0102200", "特种车损失保险");
		coverageMap.put("0102231", "玻璃单独破碎险");
		coverageMap.put("0102310", "自燃损失险");
		coverageMap.put("0102260", "新增加设备损失险");
		coverageMap.put("0102230", "修理期间费用补偿险");
		coverageMap.put("0102250", "特种车损失保险无法找到第三方特约险");
		coverageMap.put("0102252", "指定修理厂险");
		coverageMap.put("0102253", "起重、装卸、挖掘车辆损失扩展条款");
		coverageMap.put("0102254", "特种车辆固定设备、仪器损坏扩展条款");
		coverageMap.put("0102600", "特种车第三者责任保险");
		coverageMap.put("0102800", "车上货物责任险");
		coverageMap.put("0102610", "精神损害抚慰金责任险");
		coverageMap.put("0102701", "特种车车上人员责任保险（司机）");
		coverageMap.put("0102702", "特种车车上人员责任保险（乘客）");
		coverageMap.put("0102500", "特种车全车盗抢保险");
		coverageMap.put("0102911", "不计免赔率险（特种车损失保险）");
		coverageMap.put("0102912", "不计免赔率险（特种车第三者责任保险）");
		coverageMap.put("0102928", "不计免赔率险（特种车车上人员责任保险（司机））");
		coverageMap.put("0102929", "不计免赔率险（特种车车上人员责任保险（乘客））");
		coverageMap.put("0102971", "不计免赔率险（自燃损失险）");
		coverageMap.put("0102925", "不计免赔率险（车上货物责任险）");
		coverageMap.put("0102972", "不计免赔率险（精神损害抚慰金责任险）");
		coverageMap.put("0102923", "不计免赔率险（新增设备损失险）");
	}

	public static Map<String, String> insuranceAreaMap = new HashMap<String, String>();//投保地区映射
	static{
		insuranceAreaMap.put("110000", "北京");
		insuranceAreaMap.put("310000", "上海");
		insuranceAreaMap.put("120000", "天津");
		insuranceAreaMap.put("500000", "重庆");
		insuranceAreaMap.put("230000", "黑龙江");
		insuranceAreaMap.put("220000", "吉林");
		insuranceAreaMap.put("210000", "辽宁");
		insuranceAreaMap.put("130000", "河北");
		insuranceAreaMap.put("140000", "山西");
		insuranceAreaMap.put("370000", "山东");
		insuranceAreaMap.put("340000", "安徽");
		insuranceAreaMap.put("320000", "江苏");
		insuranceAreaMap.put("330000", "浙江");
		insuranceAreaMap.put("350000", "福建");
		insuranceAreaMap.put("360000", "江西");
		insuranceAreaMap.put("440000", "广东");
		insuranceAreaMap.put("460000", "海南");
		insuranceAreaMap.put("450000", "广西");
		insuranceAreaMap.put("430000", "湖南");
		insuranceAreaMap.put("420000", "湖北");
		insuranceAreaMap.put("410000", "河南");
		insuranceAreaMap.put("530000", "云南");
		insuranceAreaMap.put("520000", "贵州");
		insuranceAreaMap.put("510000", "四川");
		insuranceAreaMap.put("610000", "陕西");
		insuranceAreaMap.put("620000", "甘肃");
		insuranceAreaMap.put("640000", "宁夏");
		insuranceAreaMap.put("650000", "新疆");
		insuranceAreaMap.put("150000", "内蒙古");
		insuranceAreaMap.put("210200", "大连");
		insuranceAreaMap.put("370200", "青岛");
		insuranceAreaMap.put("330200", "宁波");
		insuranceAreaMap.put("350200", "厦门");
		insuranceAreaMap.put("440300", "深圳");
		insuranceAreaMap.put("630000", "青海");
		insuranceAreaMap.put("540000", "西藏");
	}
	
	/**
	 * 投保推荐方案版本
	 * s:简约版
	 * e:经济版
	 * l:豪华版
	 */
	public final static  String SOLUTION_VERSION_SIMPLE="s";
	public final static  String SOLUTION_VERSION_ECONOMY="e";
	public final static  String SOLUTION_VERSION_LUXURY="l";
	/**
	 * 投保推荐方案险种分类
	 * jq:交强险
	 * sy:商业险
	 * cy:财意险
	 */
	public final static  String SOLUTION_CATEGORY_TRAFFIC="jq";
	public final static  String SOLUTION_CATEGORY_BUSINESS="sy";
	public final static  String SOLUTION_CATEGORY_WEALTH_ACC="cy";
	/**
	 * 投保推荐方案根节点pcpde
	 */
	public final static  String SOLUTION_ROOT_PCODE="0";
}
