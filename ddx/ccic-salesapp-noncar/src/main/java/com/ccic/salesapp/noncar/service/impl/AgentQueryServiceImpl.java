package com.ccic.salesapp.noncar.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.AgentQueryReqVO;
import com.ccic.salesapp.noncar.dto.AgentQueryResVO;
import com.ccic.salesapp.noncar.dto.DefaultDAO;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesAgentMsgDto;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesAgreementMsgDto;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesMainMsgDto;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.interf.inner.ReimbursementWebService;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.interf.inner.ReimbursementWebServiceServiceLocator;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.interf.inner.ReimbursementWebServiceSoapBindingStub;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PRPDCompanyMapper;
import com.ccic.salesapp.noncar.service.AgentQueryService;
import com.ccic.salessapp.common.core.web.HttpResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AgentQueryServiceImpl implements AgentQueryService {
	
	@Value("agreement_search_url")
	String url;
	
	@Autowired
	PRPDCompanyMapper pRPDCompanyMapper;
	
	@Override
	public HttpResult agentQuery(AgentQueryReqVO info) throws Exception {
		HttpResult httpResult =  HttpResult.success();
		/*
		 * XStream xs=new XStream(); log.info("请求信息："+xs.toXML(info));
		 */
		
		SalesMainMsgDto salesMainMsgDto=new SalesMainMsgDto();
		SalesAgentMsgDto salesAgentMsg=new SalesAgentMsgDto();
		SalesAgreementMsgDto[] salesAgreementMsg={};
		if(null!=info.getSalesAgreementMsg()){
			salesAgreementMsg[0]=info.getSalesAgreementMsg();
		}
		if(null!=info.getSalesAgentMsg()){
			String inputDate = new SimpleDateFormat("yyyy-MM-dd")
			.format(new Date());
			info.getSalesAgentMsg().setQueryDate(inputDate);
			info.getSalesAgentMsg().setInputDate(inputDate);
			salesAgentMsg=info.getSalesAgentMsg();
		}
		salesMainMsgDto.setSalesAgentMsgDto(salesAgentMsg);
		salesMainMsgDto.setSalesAgreementMsgDto(salesAgreementMsg);
	
		
		try {
			ReimbursementWebServiceServiceLocator reimbursementWebServiceLocator = new ReimbursementWebServiceServiceLocator();
			/*
			 * SystemConfig cofig=new SystemConfig("system"); String url;
			 */
	    	//url = cofig.getResourceValue("agreement_search_url");
			reimbursementWebServiceLocator.setReimbursementWebServiceEndpointAddress(url);
			
			ReimbursementWebService reimbursementWebService;
			reimbursementWebService = reimbursementWebServiceLocator.getReimbursementWebService();
			
			ReimbursementWebServiceSoapBindingStub reimbursementWebServiceSoapBindingStub = (ReimbursementWebServiceSoapBindingStub)reimbursementWebService;
			reimbursementWebServiceSoapBindingStub.setTimeout(5000);//设置超时时间为5s
			
			SalesMainMsgDto reimbursementResponse = new SalesMainMsgDto();
			reimbursementResponse = reimbursementWebService.dealRequest(salesMainMsgDto);
			
			/* log.info("返回信息:"+xs.toXML(reimbursementResponse)); */
			
			if(reimbursementResponse!=null){
				
				AgentQueryResVO agentRes=new AgentQueryResVO();
//				agentRes.setSalesMainMsgDto(reimbursementResponse);
				if(reimbursementResponse.getSalesAgentMsgDto()!=null && StringUtils.isNotBlank(reimbursementResponse.getSalesAgentMsgDto().getComCode())){
					String comName = (String) pRPDCompanyMapper.getComName(reimbursementResponse.getSalesAgentMsgDto().getComCode());
//					agentRes.setComName(comName);
				}
				httpResult.setData(agentRes);
				httpResult.setCode("1");
				httpResult.setMsg("代理查询接口调用成功！");
				
			}else{
				httpResult.setCode("0");
				httpResult.setMsg("代理查询接口调用失败！");
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
			httpResult.setCode("0");
			httpResult.setMsg("代理查询接口调用异常");
		}    
		return httpResult;
	}
}
