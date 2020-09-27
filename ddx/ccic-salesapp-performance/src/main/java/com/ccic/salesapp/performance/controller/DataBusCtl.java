package com.ccic.salesapp.performance.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccic.salesapp.performance.domain.vo.dto.PlanFeePay;
import com.ccic.salesapp.performance.service.PlanFeePayService;
import com.ccic.salessapp.common.utils.NumberUtil;
import com.ccic.salessapp.common.utils.StringUtils;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "databus数据接收接口", description = "databus数据接收接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping( value = "progress")
@Slf4j
public class DataBusCtl {

    @Autowired
    PlanFeePayService planFeePayService;

    @PostMapping(value = "planFeeDataPush")
    @ApiOperation(value = "手续费数据更新", notes = "手续费数据更新")
    public void planFeeDataPush(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	Map<String,Object> responseMap = new HashMap<String,Object>();
    	Map<String,Object> responseHead = new HashMap<String,Object>();
    	responseHead.put("consumerSeqNo", "");
    	responseHead.put("status",0);
    	responseHead.put("esbCode","ESB-000000");
    	responseHead.put("esbMessage", "ESB服务成功");
    	responseHead.put("appCode","0");
    	responseHead.put("appMessage","数据同步成功");
    	responseMap.put("responseHead", responseHead);
    	PrintWriter out = null;
		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    InputStreamReader isr = null;
	    BufferedReader inBufReader = null;
	    String result = "";
		try{
	    	isr = new InputStreamReader(request.getInputStream(), "UTF-8");
	    	inBufReader =new BufferedReader(isr);
	    	String str = null;  
	        StringBuffer sBuffer = new StringBuffer();  
	        while ((str = inBufReader.readLine()) != null) {  
	        	sBuffer.append(str);
	        }		    
	        String reqStr = URLDecoder.decode(sBuffer.toString(),"UTF-8");//请求信息
	        log.info("推送报文:"+reqStr);
	        
	        result = this.process(reqStr);//处理
	        if("OK".equals(result)) {
        		Gson gson =	new Gson();
        		result = gson.toJson(responseMap);
        		response.setContentType("application/json; charset=UTF-8");
	        }
		}catch(Exception e){
			log.error("系统异常:"+e);
			result="error";
		}finally{
			log.info("响应报文:"+result);
	        out = response.getWriter();
    	    out.print(result);
			if(out!=null){
				out.close();
			}
			if(inBufReader!=null){
				inBufReader.close();
			}
		}
    }

    
    public String process(String reqStr){
		if(StringUtils.isBlank(reqStr)){
			return "error";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try{
			List<PlanFeePay> planFeePays = new ArrayList<PlanFeePay>();
			List<String> keys = new ArrayList<String>();
			//读取xml 转换成Document  
			Document document = DocumentHelper.parseText(reqStr);
	        //获取根节点元素对象  
	        Element root = document.getRootElement();
	        List<Element> eList = root.elements();
	        if(eList!=null && eList.size()>0){
	        	for(Element e : eList){
	                List<Element> nodes = e.elements();
	                PlanFeePay planFeePay = new PlanFeePay();
	                for(Element node : nodes){
		                if("CertiID".equals(node.getName())){
		                	planFeePay.setCertiId(node.getText());
		                }else if("PolicyNo".equals(node.getName())){
		                	planFeePay.setPolicyNo(node.getText());
		                }else if("CertiNo".equals(node.getName())){
		                	planFeePay.setCertiNo(node.getText());
		                }else if("AgentCode".equals(node.getName())){
		                	planFeePay.setAgentCode(node.getText());
		                }else if("RiskName".equals(node.getName())){
		                	planFeePay.setRiskCName(node.getText());
		                }else if("RealPoliFee".equals(node.getName())){
		                	planFeePay.setRealPoliFee(NumberUtil.strToDouble(node.getText()));
		                }else if("DisRate".equals(node.getName())){
		                	planFeePay.setDisRate(NumberUtil.strToDouble(node.getText()));
		                }else if("PlanFee".equals(node.getName())){
		                	planFeePay.setPlanFee(NumberUtil.strToDouble(node.getText()));
		                }else if("InsuredName".equals(node.getName())){
		                	planFeePay.setInsuredName(node.getText());
		                }else if("ItemAttribute".equals(node.getName())){
		                	planFeePay.setItemAttribute(node.getText());
		                }else if("WithdrawalsFlag".equals(node.getName())){
		                	planFeePay.setWithdrawalsFlag(node.getText());
		                }else if("WithdrawalsMsg".equals(node.getName())){
		                	planFeePay.setWithdrawalsMsg(node.getText());
		                }else if("WithdrawalsDate".equals(node.getName())){
		                	if (StringUtils.isNotBlank(node.getText())) {
		                		planFeePay.setWithdrawalsDate(df.parse(node.getText())); 
							}
		                }else if("PackNo".equals(node.getName())){
		                	planFeePay.setPackNo(node.getText());
		                }else if("AgreementNo".equals(node.getName())){
		                	planFeePay.setAgreementNo(node.getText());
		                }else if("AgentOrigin".equals(node.getName())){
		                	planFeePay.setAgentOrigin(node.getText());
		                }else if("BranchCode".equals(node.getName())){
		                	planFeePay.setBranchCode(node.getText());
		                }else if("Handler1Code".equals(node.getName())){
		                	planFeePay.setHandler1code(node.getText());
		                }else if("BillStatus".equals(node.getName())){
		                	planFeePay.setBillStatus(node.getText());
		                }else if("PackageDate".equals(node.getName())){
		                	if (StringUtils.isNotBlank(node.getText())) {
		                		planFeePay.setPackageDate(df.parse(node.getText())); 
							}
		                }else if("CertiType".equals(node.getName())){//==
		                	planFeePay.setCertiType(node.getText());
		                }else if("CommisionType".equals(node.getName())){
		                	planFeePay.setCommisionType(node.getText());
		                }else if("RiskCode".equals(node.getName())){
		                	planFeePay.setRiskCode(node.getText());
		                }else if("Contractno".equals(node.getName())){
		                	planFeePay.setContractno(node.getText());
		                }else if("AppliCode".equals(node.getName())){
		                	planFeePay.setAppliCode(node.getText());
		                }else if("AppliName".equals(node.getName())){
		                	planFeePay.setAppliName(node.getText());
		                }else if("InsuredCode".equals(node.getName())){
		                	planFeePay.setInsuredCode(node.getText());
		                }else if("StartDate".equals(node.getName())){
		                	if (StringUtils.isNotBlank(node.getText())) {
		                		planFeePay.setStartDate(df.parse(node.getText()));
		                	}
		                }else if("EndDate".equals(node.getName())){
		                	if (StringUtils.isNotBlank(node.getText())) {
		                		planFeePay.setEndDate(df.parse(node.getText()));
		                	}
		                }else if("UnderWriteDate".equals(node.getName())){
		                	if (StringUtils.isNotBlank(node.getText())) {
		                		planFeePay.setUnderWriteDate(df.parse(node.getText()));
		                	}
		                }else if("ValidDate".equals(node.getName())){
		                	if (StringUtils.isNotBlank(node.getText())) {
		                		planFeePay.setValidDate(df.parse(node.getText()));
		                	}
		                }else if("ComCode".equals(node.getName())){
		                	planFeePay.setComCode(node.getText());
		                }else if("MakeCom".equals(node.getName())){
		                	planFeePay.setMakeCom(node.getText());
		                }else if("AgentName".equals(node.getName())){
		                	planFeePay.setAgentName(node.getText());
		                }else if("Handler1Name".equals(node.getName())){
		                	planFeePay.setHandler1Name(node.getText());
		                }else if("HandlerCode".equals(node.getName())){
		                	planFeePay.setHandlerCode(node.getText());
		                }else if("BusinessNature".equals(node.getName())){
		                	planFeePay.setBusinessNature(node.getText());
		                }else if("ShareholderFlag".equals(node.getName())){
		                	planFeePay.setShareholderFlag(node.getText());
		                }else if("CarNatureCode".equals(node.getName())){
		                	planFeePay.setCarNatureCode(node.getText());
		                }else if("WriteOffFlag".equals(node.getName())){
		                	planFeePay.setWriteOffFlag(node.getText());
		                }else if("MainPolicyFlag".equals(node.getName())){
		                	planFeePay.setMainPolicyFlag(node.getText());
		                }else if("MaxDisRate".equals(node.getName())){
		                	planFeePay.setMaxDisRate(node.getText());
		                }else if("AccBookCode".equals(node.getName())){
		                	planFeePay.setAccBookCode(node.getText());
		                }else if("PackFlag".equals(node.getName())){
		                	planFeePay.setPackFlag(node.getText());
		                }else if("PackPrintFee".equals(node.getName())){
		                	planFeePay.setPackPrintFee(node.getText());
		                }else if("Attribute5".equals(node.getName())){
		                	planFeePay.setAttribute5(node.getText());
		                }else if("HD1ExhibitionNo".equals(node.getName())){
		                	planFeePay.sethD1ExhibitionNo(node.getText());
		                }else if("CertifiCateNo".equals(node.getName())){
		                	planFeePay.setCertifiCateNo(node.getText());
		                }else if("ProJectSerialNo".equals(node.getName())){
		                	planFeePay.setProJectSerialNo(node.getText());
		                }else if("BusinessNature2".equals(node.getName())){
		                	planFeePay.setBusinessNature2(node.getText());
		                }else if("BusinessNature2Name".equals(node.getName())){
		                	planFeePay.setBusinessNature2Name(node.getText());
		                }else if("Handler2Code".equals(node.getName())){
		                	planFeePay.setHandler2Code(node.getText());
		                }else if("Handler2Name".equals(node.getName())){
		                	planFeePay.setHandler2Name(node.getText());
		                }else if("BillNo".equals(node.getName())){
		                	planFeePay.setBillNo(node.getText());
		                }else if("ReimNo".equals(node.getName())){
		                	planFeePay.setReimNo(node.getText());
		                }else if("PayInfoStatus".equals(node.getName())){
		                	planFeePay.setPayInfoStatus(node.getText());
		                }else if("CallBackFlag".equals(node.getName())){
		                	planFeePay.setCallBackFlag(node.getText());
		                }else if("NeedCallBack".equals(node.getName())){
		                	planFeePay.setNeedCallBack(node.getText());
		                }else if("AgencyCode".equals(node.getName())){
		                	planFeePay.setAgencyCode(node.getText());
		                }else if("RealPremium".equals(node.getName())){
		                	planFeePay.setRealPremium(node.getText());
		                }else if("AuditDate".equals(node.getName())){
		                	if (StringUtils.isNotBlank(node.getText())) {
		                		planFeePay.setAuditDate(df.parse(node.getText()));
		                	}
		                }else if("ClassCode".equals(node.getName())){
		                	planFeePay.setClassCode(node.getText());
		                }
		            }
		            planFeePays.add(planFeePay);
		            keys.add(planFeePay.getCertiId());
	        	}
	        }
	        
	        
	        planFeePayService.updatePlanFee(planFeePays);
	        log.info("更新記錄"+keys );
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("DataBus数据接收异常", e);
			return "error" + e.getMessage();
		}

		return "OK";
	}



}
