package com.ccic.salesapp.noncar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.order.group.sales.GroupRequest;
import com.ccic.salesapp.noncar.dto.order.group.sales.GroupResponse;
import com.ccic.salesapp.noncar.dto.order.group.sales.GroupResponseBody;
import com.ccic.salesapp.noncar.service.GroupOrderRequestService;
import com.ccic.salesapp.noncar.service.HttpService;
import com.ccic.salessapp.common.core.exception.PlatformBaseException;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseBody;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
@Service
@Slf4j
public class GroupOrderRequestServiceImpl implements  GroupOrderRequestService{
	
	@Value("${esb.address.calculateAndGenerate}")
	String calculateAndGenerate;
	
	@Autowired
	HttpService httpService;
	
	public static JsonConfig jsonConfig = new JsonConfig();
	static{
		jsonConfig.setJsonPropertyFilter(new PropertyFilter(){  
		    @Override  
		    public boolean apply(Object source, String name, Object value)  
		    {  
		    	return value == null;
		    }
		    
		});
		jsonConfig.addIgnoreFieldAnnotation(Request.class);
		
	}
	
	
	@Override
	public GroupResponse requestPrice(GroupRequest request, String bussNo) throws Exception{ 
		
		String reqJson = JSONObject.fromObject(request,jsonConfig).toString();
    	log.info(bussNo+"调用销售中台试算接口开始，请求报文："+ reqJson);
    	String respJson = httpService.postJsonRequest(bussNo,reqJson, calculateAndGenerate);
    	//String respJson = "{\"salesResponseBody\":{\"belongToHandler2Code\":\"8000506130\",\"belongToHandlerCode\":\"8000506130\",\"businessAttribute\":\"0801\",\"businessSource2Code\":\"0101\",\"businessSourceCode\":\"0101\",\"businessType\":\"1\",\"channelByPerson\":\"C0000023\",\"channelOpInfoList\":[{\"channelCode\":\"310073\",\"channelComCode\":\"310073\",\"channelComName\":\"移动销售支持\",\"channelName\":\"大地行\",\"channelPlanName\":\"XYZ2050020\",\"channelProductCode\":\"WVB\",\"channelSeqNo\":\"20200922193804-0001023170674\",\"quotaPlanCode\":\"SXYZ20500200001\",\"trxDate\":\"2020-09-22 19:38:04\"}],\"discountRate\":0.32447,\"duePremium\":310.52,\"effectiveDate\":\"2020-09-22 00:00:00\",\"expiryDate\":\"2021-09-21 23:59:59\",\"groupPlanCode\":\"XYZ2050020\",\"handlerCode\":\"8000506130\",\"invoiceInfoList\":[{}],\"isIssueAfterPay\":\"Y\",\"issueOrgCode\":\"34011902\",\"issueUserCode\":\"8000506130\",\"orgCode\":\"34011902\",\"policyCustomerList\":[{\"age\":43,\"customerName\":\"qqqqq\",\"customerRoleCode\":\"1\",\"idNo\":\"1101075015\",\"idType\":\"414\",\"indiDateOfBirth\":\"1977-03-07\",\"indiGenderCode\":\"1\",\"indiMobile\":\"16666666666\",\"partyCategory\":\"01\"},{\"age\":43,\"customerName\":\"被bao人\",\"customerRoleCode\":\"2\",\"idNo\":\"1101075015\",\"idType\":\"414\",\"indiDateOfBirth\":\"1977-03-07\",\"indiGenderCode\":\"1\",\"indiMobile\":\"16666666666\",\"partyCategory\":\"01\",\"polHolderInsuredRelaCode\":\"01\"}],\"policyLobList\":[{\"childPlanCode\":\"WVB20Z0003\",\"duePremium\":245.95,\"isMainProduct\":\"Y\",\"numberOfCopies\":1,\"policyRiskList\":[{\"age\":43,\"customerName\":\"被bao人\",\"customerRoleCode\":\"2\",\"dateOfBirth\":\"1977-03-07\",\"idNo\":\"1101075015\",\"idType\":\"414\",\"indiGenderCode\":\"1\",\"indiMobile\":\"16666666666\",\"insuredName\":\"被bao人\",\"partyCategory\":\"01\",\"polHolderInsuredRelaCode\":\"01\",\"policyCoverageList\":[{\"clauseCode\":\"CF1100290\",\"coverageCode\":\"C100468\",\"duePermium\":0.32,\"premiumBeforeDiscount\":1,\"sumInsured\":1000000},{\"clauseCode\":\"CF1100290\",\"coverageCode\":\"C100590\",\"duePermium\":0.32,\"premiumBeforeDiscount\":1,\"sumInsured\":100000},{\"clauseCode\":\"CF1100290\",\"coverageCode\":\"C100470\",\"duePermium\":0.32,\"premiumBeforeDiscount\":1,\"sumInsured\":100000},{\"clauseCode\":\"CF1100290\",\"coverageCode\":\"C100469\",\"duePermium\":0.32,\"premiumBeforeDiscount\":1,\"sumInsured\":100000},{\"clauseCode\":\"CF2100879\",\"coverageCode\":\"C101255\",\"duePermium\":9.73,\"premiumBeforeDiscount\":30,\"sumInsured\":1000000},{\"clauseCode\":\"CF2100878\",\"coverageCode\":\"C101256\",\"duePermium\":55.16,\"premiumBeforeDiscount\":170,\"sumInsured\":1000000},{\"clauseCode\":\"CF1101232\",\"coverageCode\":\"C200511\",\"duePermium\":107.85,\"premiumBeforeDiscount\":332.4,\"sumInsured\":3000000},{\"clauseCode\":\"CF1101232\",\"coverageCode\":\"C200512\",\"duePermium\":71.9,\"premiumBeforeDiscount\":221.6,\"sumInsured\":3000000}],\"riskType\":\"70000\",\"socialSecurityFlag\":\"N\"}],\"premiumBeforeDiscount\":758,\"productCode\":\"WVB\",\"productLine\":\"70000\",\"proposalNo\":\"TWVB20340119020000000021\",\"submissionNo\":\"CZ203401011700124\",\"sumInsured\":9300000},{\"childPlanCode\":\"EXA20Z0001\",\"duePremium\":61.97,\"isMainProduct\":\"N\",\"numberOfCopies\":1,\"policyRiskList\":[{\"age\":43,\"customerName\":\"被bao人\",\"customerRoleCode\":\"2\",\"dateOfBirth\":\"1977-03-07\",\"idNo\":\"1101075015\",\"idType\":\"414\",\"indiGenderCode\":\"1\",\"indiMobile\":\"16666666666\",\"insuredName\":\"被bao人\",\"partyCategory\":\"01\",\"polHolderInsuredRelaCode\":\"01\",\"policyCoverageList\":[{\"clauseCode\":\"CF2100197\",\"coverageCode\":\"C100512\",\"duePermium\":18.59,\"premiumBeforeDiscount\":57.3,\"sumInsured\":10000},{\"clauseCode\":\"CF2100189\",\"coverageCode\":\"C100528\",\"duePermium\":12.39,\"premiumBeforeDiscount\":38.2,\"sumInsured\":27000},{\"clauseCode\":\"CF1100281\",\"coverageCode\":\"C100416\",\"duePermium\":30.99,\"premiumBeforeDiscount\":95.5,\"sumInsured\":300000}],\"riskType\":\"70000\",\"socialSecurityFlag\":\"N\"}],\"premiumBeforeDiscount\":191,\"productCode\":\"EXA\",\"productLine\":\"70000\",\"proposalNo\":\"TWVB20340119020000000021\",\"submissionNo\":\"CZ203401011700124\",\"sumInsured\":337000},{\"childPlanCode\":\"WVI20Z0002\",\"duePremium\":2.6,\"isMainProduct\":\"N\",\"numberOfCopies\":1,\"policyRiskList\":[{\"age\":43,\"customerName\":\"被bao人\",\"customerRoleCode\":\"2\",\"dateOfBirth\":\"1977-03-07\",\"idNo\":\"1101075015\",\"idType\":\"414\",\"indiGenderCode\":\"1\",\"indiMobile\":\"16666666666\",\"insuredName\":\"被bao人\",\"partyCategory\":\"01\",\"polHolderInsuredRelaCode\":\"01\",\"policyCoverageList\":[{\"clauseCode\":\"CF2100892\",\"coverageCode\":\"C101266\",\"duePermium\":1.95,\"premiumBeforeDiscount\":6,\"sumInsured\":50000},{\"clauseCode\":\"CF1101258\",\"coverageCode\":\"C101268\",\"duePermium\":0.65,\"premiumBeforeDiscount\":2,\"sumInsured\":1500}],\"riskType\":\"70000\",\"socialSecurityFlag\":\"N\"}],\"premiumBeforeDiscount\":8,\"productCode\":\"WVI\",\"productLine\":\"70000\",\"proposalNo\":\"TWVB20340119020000000021\",\"submissionNo\":\"CZ203401011700124\",\"sumInsured\":51500}],\"premiumBeforeDiscount\":957,\"proposalDate\":\"2020-09-22\",\"salesPlanCode\":\"SXYZ20500200001\"},\"salesResponseHead\":{\"consumerSeqNo\":\"00f109fe-e5da-4a4a-a37d-38da9bd9bc56\",\"providerSeqNo\":\"9a922596d1a43c8c\",\"seqNo\":\"1\"}}";
		log.info(bussNo+"调用销售中台试算接口结束，返回报文："+ respJson);
		
		ResponseHead responseHead = null;
		ResponseBody responseBody = null;
		JSONObject json = JSONObject.fromObject(respJson);
		responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("salesResponseHead"), ResponseHead.class);
		if(responseHead.getStatus()==0){
			responseBody = groupResponseBody(json.getJSONObject("salesResponseBody").toString());
			log.info("调用销售中台试算接口成功");//响应信息
		}else{
			log.info("调用销售中台试算接口失败");//响应信息
			String msg = responseHead.getAppCode()+":"+responseHead.getAppMessage();
			throw new PlatformBaseException(msg, 0);
		}
		GroupResponse response = new GroupResponse();
		response.setResponseHead(responseHead);
		response.setResponseBody(responseBody);
		return response;
		
	}
	
	
	@Override
	public GroupResponse requestOrder(GroupRequest request, String bussNo) throws Exception{ 
		
		String reqJson = JSONObject.fromObject(request,jsonConfig).toString();
    	log.info(bussNo+"调用销售中台接口开始，请求报文："+ reqJson);
    	String respJson = httpService.postGroupRequest(bussNo,reqJson, calculateAndGenerate);
    	//String respJson = "{\"salesResponseBody\":{\"belongToHandler2Code\":\"8000546679\",\"belongToHandlerCode\":\"8000546679\",\"businessAttribute\":\"0801\",\"businessSource2Code\":\"0101\",\"businessSourceCode\":\"0101\",\"businessType\":\"1\",\"duePremium\":\"800.00\",\"effectiveDate\":\"2020-08-30 00:00:00\",\"expiryDate\":\"2021-08-29 23:59:59\",\"groupPlanCode\":\"XYZ2050020\",\"isIssueAfterPay\":\"N\",\"orgCode\":\"33015473\",\"policyLobList\":[{\"isMainProduct\":\"Y\",\"policyRiskList\":[{\"customerName\":\"完全21\",\"customerRoleCode\":\"2\",\"dateOfBirth\":\"1981-01-17\",\"idNo\":\"320106198101170477\",\"idType\":\"111\",\"numberOfCopies\":1,\"partyCategory\":\"01\",\"planCode\":\"WVI2050803\",\"polHolderInsuredRelaCode\":\"1\",\"policyCoverageList\":[{\"clauseCode\":\"CF1100290\",\"coverageCode\":\"C100470\",\"duePremium\":\"1\"}],\"sequenceNumber\":1,\"socialSecurityFlag\":\"N\"},{\"customerName\":\"完全2222\",\"customerRoleCode\":\"2\",\"dateOfBirth\":\"1981-01-17\",\"idNo\":\"320106198101170477\",\"idType\":\"111\",\"numberOfCopies\":1,\"partyCategory\":\"01\",\"planCode\":\"WVI2050803\",\"polHolderInsuredRelaCode\":\"1\",\"policyCoverageList\":[{\"clauseCode\":\"CF1100290\",\"coverageCode\":\"C100470\",\"duePremium\":\"1\"}],\"sequenceNumber\":1,\"socialSecurityFlag\":\"N\"},{\"Address\":\"民生路222弄\",\"FullAddress\":\"江苏省南京市江宁区民生路222弄\",\"InsuredItemName\":\"房屋及其室内附属设施\",\"InsuredName\":\"房屋及其室内附属设施\",\"ItemCategory\":\"完全\",\"PostCode\":\"100086\",\"RegionCode\":\"110101\",\"insuredCategory\":\"01\",\"numberOfCopies\":1,\"planCode\":\"WVI2050813\",\"policyCoverageList\":[{\"clauseCode\":\"CF1100290\",\"coverageCode\":\"C100470\",\"isMainCoverage\":\"Y\"}]},{\"insuredName\":\"建行信用卡\",\"numberOfCopies\":1,\"planCode\":\"WVI20508123\",\"policyCoverageList\":[{\"clauseCode\":\"CF1100290\",\"coverageCode\":\"C100470\",\"isMainCoverage\":\"Y\"}],\"productName\":\"11111\"}]}],\"premiumBeforeDiscount\":\"1000.00\",\"premiumCurrencyCode\":\"CNY\",\"proposalDate\":\"2020-04-20\"},\"salesResponseHead\":{\"SeqNo\":\"1\",\"ConsumerSeqNo\":\"1\"}}";
		log.info(bussNo+"调用销售中台接口结束，返回报文："+ respJson);
		
		ResponseHead responseHead = null;
		ResponseBody responseBody = null;
		JSONObject json = JSONObject.fromObject(respJson);
		responseHead = (ResponseHead) JSONObject.toBean(json.getJSONObject("salesResponseHead"), ResponseHead.class);
		if(responseHead.getStatus()==0){
			responseBody = groupResponseBody(json.getJSONObject("salesResponseBody").toString());
			log.info("调用销售中台下单接口成功");//响应信息
		}else{
			log.info("调用销售中台下单接口失败");//响应信息
			String msg = responseHead.getAppCode()+":"+responseHead.getAppMessage();
			throw new PlatformBaseException(msg, 0);
		}
		GroupResponse response = new GroupResponse();
		response.setResponseHead(responseHead);
		response.setResponseBody(responseBody);
		return response;
		
	}
	
	
	
	private GroupResponseBody groupResponseBody(String respJson) {
		GroupResponseBody responseBody =  JSON.parseObject(respJson,GroupResponseBody.class);
		return responseBody;
	}
	
	
}
