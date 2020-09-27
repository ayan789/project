package com.ccic.salesapp.noncar.exception;

/**
 * <p>Title: 定义用户登录后的消息常量，如用户ID和用户名称等</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public final class WebConstants {
//	public static String title = "title";
	public final static String SYSTEM_PARAMETER="systemParam";
//	public static String LOGINID="loginID";//登录ID;
//	public static String USERS="users";//所有用户;
//	public static String TRUE_NAME="trueName";//登录者真实名称;
//	public static String USER_ID="userID";//用户ID（主键）;
//	public static String OPERTIONS="opertions";//具体操作;
//	public static String ORGID_NAME="orgIDName";//部门ID和名字;
//	public static String INNERNOTE_TYPE="innernoteType";   //内部短消息类型hashTable名
	public final static String ORG_LIST="orgList";   //所有组织部门orgList名
//	public static String OFFICE_PHONE="officePhone";   //办公室电话


	public final static String USER_INFO="userInfo";		//用户信息

	/*
	 * 应用程序定义
	 * 1--保险代理业务系统;2--车险移动定损系统;3--保险公估业务系统；4--寿险代理人展业系统.
	 */
	public final static String APP_DEF_AGE = "1";
	public final static String APP_DEF_CAR = "2";
	public final static String APP_DEF_ADG = "3";
	public final static String APP_DEF_EXH = "4";

	//PDA应用更新使用
	public final static String SERV_FILE_PATH = "\\MobileUpdate\\";//服务器端更新目录相对路径
	public final static String PDA_FILE_PATH = "\\Program Files\\estar\\";//PDA端安装目录相对路径

	/*
	 * 用户应用状态：
	 * 1--未启用;2--已开通;3--已停用.
	 */
	public final static String APP_STATE_NOTURNON = "1";
	public final static String APP_STATE_TURNON = "2";
	public final static String APP_STATE_STOP = "3";

	/*
	 * 用户应用类型：
	 * 1--WEB普通用户;2--PDA普通用户;3--PDA黄金用户;	 * 4--PDA铂金用户;5--PDA钻石用户.
	 */
	public final static String USER_TYPE_WEB = "1";
	public final static String USER_TYPE_COMM = "2";
	public final static String USER_TYPE_GOLD = "3";
	public final static String USER_TYPE_PLAT = "4";
	public final static String USER_TYPE_DIAM = "5";

	/*
	 * 用户服务状态
	 * 1--服务申请；2--申请提交；3--财务确认；4--服务激活；5--服务欠费；6--服务终止。
	 */
	public final static String USE_STATE_APPL = "1";
	public final static String USE_STATE_SUBM = "2";
	public final static String USE_STATE_AFFI = "3";
	public final static String USE_STATE_ACTI = "4";
	public final static String USE_STATE_ARRE = "5";
	public final static String USE_STATE_STOP = "6";

	/*
	 * 注册用户所选数据分类
	 * 1--公用数据；2--寿险公司；3--产险公司；4--公估公司;5--地区费率;6--代理公司；7--车险条款；8--地区条款。。
	 */
	public final static String DATA_TYPE_COMM = "1";
	public final static String DATA_TYPE_LIFE = "2";
	public final static String DATA_TYPE_PROP = "3";
	public final static String DATA_TYPE_ESTI = "4";
	public final static String DATA_TYPE_CAR = "5";
	public final static String DATA_TYPE_AGEN = "6";
	public final static String DATA_TYPE_CARITEM = "7";
	public final static String DATA_TYPE_REGITEM = "8";

	/*
	 * 操作系统类型
	 * 1--PPC；2--PC
	 */
	public final static String OS_TYPE_PPC = "1";
	public final static String OS_TYPE_PC = "2";

}
