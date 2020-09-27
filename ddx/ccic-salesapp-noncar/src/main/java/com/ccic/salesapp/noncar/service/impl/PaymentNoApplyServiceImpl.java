package com.ccic.salesapp.noncar.service.impl;

import java.text.SimpleDateFormat;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.dto.InsuranceApplicationStatus;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApply;
import com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyResponse;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PaymentNoApplyMapper;
import com.ccic.salesapp.noncar.service.PaymentNoApplyService;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.service.AbstractService;

@Slf4j
@Service
public class PaymentNoApplyServiceImpl extends AbstractService implements PaymentNoApplyService{
	
	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	PaymentNoApplyMapper paymentNoApplyMapper;
	
	@Autowired
	PaymentNoApply paymentNoApply;

	
	@Override
	public StoreInsureInfo selectBySureInfoNo(ApplyPayVo applyPayVo) {
		return paymentNoApplyMapper.selectBySureInfoNo(applyPayVo);
	}

	@Override
	public StoreInsureInfo saveApplyPayment(StoreInsureInfo insureInfo,ResultVO result) {
		com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequestDTO paymentNoApplyRequestDTO = new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequestDTO();
		com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO proposalInfoDTO = new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.ProposalInfoDTO();
		proposalInfoDTO.setCertiNo(insureInfo.getInsureNo());//投保单号
		proposalInfoDTO.setComCode(insureInfo.getComCode());//机构代码
		proposalInfoDTO.setInsuranceCode(insureInfo.getInsuranceCode());//险种代码
		proposalInfoDTO.setEffectDate(datef.format(insureInfo.getStartDate()));//起保日期
		proposalInfoDTO.setAmount(Double.parseDouble(insureInfo.getSumPremium()));//金额
		paymentNoApplyRequestDTO.setSumFee(Double.parseDouble(insureInfo.getSumPremium()));
		paymentNoApplyRequestDTO.getProposalInfoDTO().add(proposalInfoDTO);
		paymentNoApplyRequestDTO.setSystemCode("00");//申请系统
		paymentNoApplyRequestDTO.setPayType("1");//支付方式
		paymentNoApplyRequestDTO.setSumFee(Double.parseDouble(insureInfo.getSumPremium()));//汇总金额
		result.setCode("1");//响应代码 1-成功，0-失败
		result.setMessage("申请支付号成功");//响应信息
		RequestHeadDTO head=new RequestHeadDTO();
		com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequest request=new com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApplyRequest();
		head.setConsumerID("supsale");
		head.setSeqNo("");
		request.setRequestHead(head);
		request.setRequestBody(paymentNoApplyRequestDTO);
		//payment=(PaymentNoApply)this.getBean("paymentNoApply");
		try {
			PaymentNoApplyResponse response = paymentNoApply.paymentNoApply(request);
			System.out.println(JSONObject.toJSONString(response)); 
			if (response.getResponseHead().getStatus()!=0) {
				result.setCode("0");
				result.setMessage("调用申请支付号接口出错：返回信息："+response.getResponseHead().getAppMessage());
			}else{
				insureInfo.setPayApplyNo(response.getResponseBody().getPayApplyNo());//获取支付号
				insureInfo.setIdentityCode(response.getResponseBody().getIdentityCode());//获取验证码
				insureInfo.setStatus(InsuranceApplicationStatus.DAIZHIFU);//待支付
			}
		} catch (Exception e) {
			log.error("调用申请支付号接口异常：", e);
			result.setCode("0");
			result.setMessage("调用申请支付号接口异常："+e.getMessage());	
		}
		updateInsureInfo(insureInfo, result);//调用保存下单方法更新记录
		return insureInfo;
	}
	

	public int updateInsureInfo(StoreInsureInfo insureInfo,
			ResultVO result) {
		int row =0;
		try {
			row = paymentNoApplyMapper.doUpdate(insureInfo);
		} catch (Exception e) {
			log.info("投保单信息更新失败！");
		}
		return (Integer) row;
	}

}
