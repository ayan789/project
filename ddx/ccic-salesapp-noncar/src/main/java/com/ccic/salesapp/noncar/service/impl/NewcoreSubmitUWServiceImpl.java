package com.ccic.salesapp.noncar.service.impl;
import java.util.HashMap;
import java.util.Map;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ccic.salesapp.noncar.dto.request.SubmitUnderWriteRequest2;
import com.ccic.salesapp.noncar.dto.response.SubmitUnderWriteResponseVO;
import com.ccic.salesapp.noncar.dto.response.SubmitUnderwriteResponse;
import com.ccic.salesapp.noncar.repository.basedb.mapper.InsuranceApplicationMapper;
import com.ccic.salesapp.noncar.repository.basedb.po.InsuranceApplication;
import com.ccic.salesapp.noncar.service.NewcoreSubmitUWService;
import com.ccic.salesapp.noncar.service.SubmitUWService;
import com.ccic.salessapp.common.config.WebContants;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.outService.rest.common.bean.Response;
import com.ccic.salessapp.common.request.LoginUser;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NewcoreSubmitUWServiceImpl implements NewcoreSubmitUWService {
	
	@Autowired
	InsuranceApplicationMapper insuranceApplicationMapper;
	@Autowired
	SubmitUWService submitUWService;
	
	@Override
	public HttpResult<SubmitUnderWriteResponseVO> submitUW(String orderNo, String proposalNo,String riskCode,LoginUser loginUser) throws Exception {
		HttpResult<SubmitUnderWriteResponseVO> httpResult = HttpResult.success();
		SubmitUnderWriteRequest2 requestBody=new SubmitUnderWriteRequest2();
		try {
			requestBody=this.convertRequest(orderNo,proposalNo,riskCode,loginUser);
		} catch (Exception e1) {
			log.error("封装请求信息异常：", e1);
		}
		Response response=new Response();
		SubmitUnderwriteResponse responseBody=new SubmitUnderwriteResponse();
		
		try {
			response=submitUWService.submitUW(requestBody);
			if(response.getResponseHead().getStatus()==0){
				responseBody=(SubmitUnderwriteResponse) response.getResponseBody();
//				if("3".equals(responseBody.getUwType())){//自核通过
//					result.setCode("1");
//					result.setMessage("提交核保成功");		
//				}
//				if("4".equals(responseBody.getUwType())){//人工核保
//					result.setCode("0");
//					result.setMessage("请前往人工核保,核保级别："+responseBody.getUwLevel());	
//				}
				SubmitUnderWriteResponseVO submitUnderWriteResponseVO = new SubmitUnderWriteResponseVO();
				if(responseBody!=null) {
					submitUnderWriteResponseVO.setUwType(responseBody.getUwType());
				}
				 httpResult.setData(submitUnderWriteResponseVO); 
				 httpResult.setCode("1");
				 httpResult.setMsg("提交核保成功");
				 
				this.updateInsuranceApp(proposalNo, InsuranceApplicationStatus.HEBAOZHONG);//更新核保状态
			}else{
//				if("recalculate".equals(response.getResponseHead().getAppCode())){
//					this.updateInsuranceApp(proposalNo,InsuranceApplicationStatus.HEBAOSHIBAI);//如果重新报价，走投保单修改流程
//				}
				httpResult.setCode("0");
				httpResult.setMsg(response.getResponseHead().getAppMessage());
			}
		} catch (Exception e) {
			log.error("调用提交核保接口异常：", e);
			httpResult.setCode("0");
			httpResult.setMsg("调用提交核保接口异常："+e.getMessage());
		}
		
		return httpResult;
	}
	
	/**
	 * 封装提交核保请求对象
	 * @throws Exception 
	 * */
	public SubmitUnderWriteRequest2 convertRequest(String orderNo, String proposalNo, String riskCode,LoginUser loginUser) throws Exception {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("proposalNo", proposalNo);
		if(StringUtils.isNotBlank(loginUser.getChannelCode())){
			paramMap.put("nclUserCode", loginUser.getUserCode());//外部渠道
		}else{
			paramMap.put("userCode", loginUser.getUserCode());//业务员代码
		}
		
		InsuranceApplication insuranceApp = insuranceApplicationMapper.selectOrderDetailsInquiry(paramMap);
		
		SubmitUnderWriteRequest2 coreRequestBody = new SubmitUnderWriteRequest2();
		if(insuranceApp!=null){
		    coreRequestBody.setBusinessNo(insuranceApp.getApplicationNo());//业务单号-投保单号
		    coreRequestBody.setOperator(insuranceApp.getOperatorNo());//操作员
		    if ("mpplat".equals(loginUser.getChannelCode())) {
		    	 coreRequestBody.setBusinessAttribute(WebContants.C_BUSINESS_SOURCE_ATTRIBUTE_MPPLAT); // 小程序业务属性
			}else {
				 coreRequestBody.setBusinessAttribute(WebContants.C_BUSINESS_SOURCE_ATTRIBUTE);//业务属性
			}
		   
				
		}
		return coreRequestBody;
	}
	
	
	/**
	 * 更新核保状态
	 * @param proposalNo
	 * @param  
	 * @throws Exception
	 */
	@Transactional
	public void updateInsuranceApp(String proposalNo, String status) throws Exception{
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("proposalNo", proposalNo);
		InsuranceApplication insuranceApp = insuranceApplicationMapper.selectOrderDetailsInquiry(paramMap);
		insuranceApp.setStatus(status);//更新核保状态
		try{
			//更新投保单信息
			insuranceApplicationMapper.updateByPrimaryKey(insuranceApp);
		}catch(Exception e){
			log.error("更新投保单信息失败", e);
		}
	}
}
