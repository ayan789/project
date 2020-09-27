package com.ccic.salessapp.common.request;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户信息VO*
 */

@Data
public class UserToken implements Serializable {
	private static final long serialVersionUID = -2167220640077746597L;

	private String userCode; //员工代码
	private String userName; //员工名称	
	private String userEName; //员工英文名称
	private String makeCompany; //出单机构编码
	private String makeCompanyCName; //出单机构名称
	private String phone; //电话号码
	private String mobile;  //	手机号码
	private String address;  //通信地址
	private String postcode;  //邮编
	private String email;  //邮箱
	private String valid;  //有效性
	private String deviceToken;//消息推送标志--20130516增
	private String system;//消息推送标志--20131216增
	private String version;//版本号   20131220增加
	private String terminalType;//终端类型 android/Iphone 20140123 add
	private String comRole;//机构保费查看资格 1 有资格 0没有。20141104 增加
	
	private String loginFlag;// 1-为手机验证码登陆 0-为其他
	private String storeCode;//门店代码
	
	private String handlerCode;// 归属经办人代码   
	private String handlerName;//归属经办人名称   
	private String comCode;//归属机构代码
	private String comName;//归属机构名称
	private String agentCode;//代理人代码
	private String channelCode;//从人渠道
	private String isSaleMan;//1-销售人员 0-非销人员
	
	private String xinHuaComCode;//新华所属机构
	private String xinHuaUserCode;//新华用户工号
	private String xinHuaUserName;//新华用户姓名
	private String xinHuaBusinessNature;//新华-代理业务来源
	private String xinHuaAgentCode;//新华-代理人代码
	private String xinHuaAgreementNo;//新华-代理人协议
	
	private String isVipManager;//是否为vip管家
	
	private String salaryPassword;//薪酬查询密码

	private String isAuditMan;	//是否审核员
	
	private String recommendCode;//业务员推荐码
	
	private String rankname;//职级
	
	private String homePermissionInfo;//首页展示权限
	private String teamcode;//团队代码
	private String teamname;//团队名称
	
	private String comLevel;//机构级别
	private String branchCode;//分公司代码
	private String branchName;//分公司名称
	private String userPhone;//薪酬查询手机号
	
	private String isTeamLeader;//（1是，0否）
	private String entrydate;//入职时间
    private String salesdate;//转销售时间
	
}
