package com.ccic.salesapp.noncar.dto.response;
import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.AccidentalInjuryInfo;
import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InvoiceInfo;
import com.ccic.salesapp.noncar.dto.PupilName;
import com.ccic.salesapp.noncar.dto.ZzbInsureInfo;
import com.ccic.salesapp.noncar.dto.request.CarInfoVO;
import com.ccic.salesapp.noncar.repository.databusdb.po.StoreFormulas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("订单详情response")
public class StoreInsureInfoFormulasResponseVO  {

	@ApiModelProperty("物理主键")
	private Long id;   

	@ApiModelProperty("投保单号")
	private String insureNo;   

	@ApiModelProperty("保单号")
	private String policyNo;   

	@ApiModelProperty("状态")
	private String status; 

	@ApiModelProperty(" 险种代码")
	private String insuranceCode;  

	@ApiModelProperty("险种名称")
	private String insuranceName;   

	@ApiModelProperty("保单起期")
	private String startDate;   

	@ApiModelProperty("保单止期")
	private String endDate;   

	@ApiModelProperty("总保费")
	private String sumPremium;   

	@ApiModelProperty("总保额")
	private String sumAmount;   

	@ApiModelProperty("支付号")
	private String payApplyNo;   

	@ApiModelProperty("支付验证码")
	private String identityCode;   

	@ApiModelProperty("投保人姓名")
	private String applicantName;   

	@ApiModelProperty("投保人身份证号码")
	private String appliIdentify;   

	@ApiModelProperty("投保人证件类型")
	private String appliIdentifyType;	

	@ApiModelProperty("投保人出生日期")
	private String applicantBirthDate;	

	@ApiModelProperty("投保人手机号")
	private String mobileNo;   

	@ApiModelProperty("投保人邮箱")
	private String applicantEmail;

	@ApiModelProperty(" 与被保人关系")
	private String relateInsured;
	//------------------------------------
	@ApiModelProperty("被保人姓名")
	private String insuredName;   

	@ApiModelProperty("被保人身份证号码")
	private String insuredIdentify;   

	@ApiModelProperty(" 被保险人证件类型")
	private String insuredIdentifyType;		

	@ApiModelProperty("被投保人出生日期")
	private String insuredBirthDate;	

	@ApiModelProperty("被保人手机号")
	private String insuredMobile; 

	@ApiModelProperty("被保人邮箱")
	private String email;

	@ApiModelProperty("社保类型")
	private String insuredSheBaoType;

	@ApiModelProperty("所属用户")
	private String address;   

	@ApiModelProperty("方案代码")
	private String formulaCode;   

	@ApiModelProperty("方案名称")
	private String formulaName;   

	@ApiModelProperty("被监护人出生日期，生日")
	private String beGuardianBirthDate;	 

	@ApiModelProperty("被监护人性别")
	private String beGuardianSex;	

	@ApiModelProperty("家庭成员意外伤害保险被保险人信息")
	private List<AccidentalInjuryInfo> accidentalInjuryInfos;  

	@ApiModelProperty("车辆信息")
	private CarInfoVO carInfoVO;

	@ApiModelProperty("")
	private String additionalCode;

	@ApiModelProperty("监护人姓名")
	private List<PupilName> pupilNameList;	 

	@ApiModelProperty("产品信息")
	private List<StoreFormulas> formulas;

	@ApiModelProperty(" 掌中宝订单信息附表")
	private ZzbInsureInfo zzbInsureInfo;   

	@ApiModelProperty("露天展台 0露天 1非露天") 
	private String isOpenair;

	@ApiModelProperty("能否支付：1是0否")
	private String payFlag;

	@ApiModelProperty("产品代码")
	private String productCode;

	@ApiModelProperty("发票信息")
	private InvoiceInfo invoiceInfo;

	@ApiModelProperty("待支付剩余时间,精确到秒")
	private String intervalDate;

	@ApiModelProperty("订单号")
	private String orderNo;

	@ApiModelProperty("是否代理")
	private String isAgent;

	@ApiModelProperty("代理人信息")
	private AgentInfoVO agentInfo; 

	@ApiModelProperty("1:见费  0：非见费")
	private String isSeeFee;
	
	@ApiModelProperty("保障内容")
	private Map<String,String> content;
}



