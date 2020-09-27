package com.ccic.salesapp.noncar.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.response.ResultVO;
import com.ccic.salesapp.noncar.dto.response.StoreOracleResponseVO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.PaymentNoApplyMapper;
import com.ccic.salesapp.noncar.service.PaymentNoApplyFacade;
import com.ccic.salesapp.noncar.service.impl.PaymentNoApplyFacadeImpl;
import com.ccic.salessapp.common.core.web.HttpResult;

@Slf4j
@RestController
public class PaymentNoApplyController {
	@Autowired
	PaymentNoApplyMapper paymentNoApplyMapper;
	@Autowired
	PaymentNoApplyFacade spl;
	
	//申请支付号
	@PostMapping("/applyPay")
	public HttpResult<?> applyPay(@RequestBody ApplyPayVo applyPayVo){
		applyPayVo.setInsureNo("TQJA201831010000000006");
		ResultVO result = new ResultVO();
		StoreOracleResponseVO respBody = new StoreOracleResponseVO();
		StoreInsureInfo storeInsureInfo = paymentNoApplyMapper.selectBySureInfoNo(applyPayVo);//insureNo
		System.out.println(JSONObject.toJSON(storeInsureInfo));
		StoreInsureInfo saveApplyPayment = spl.saveApplyPayment(storeInsureInfo,result);
		if ("1".equals(result.getCode())) {
			respBody.setInsureInfo(saveApplyPayment);
		} else {
			log.info("##########申请支付号失败。############");
		}
		HttpResult res = HttpResult.success("");
		res.setCode("1");
		res.setMsg("查询成功");
		return res;
	}
}
