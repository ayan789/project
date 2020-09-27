package com.ccic.salesapp.noncar.service;
 
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.response.ResultVO;

public interface PaymentNoApplyService {
	StoreInsureInfo selectBySureInfoNo(ApplyPayVo applyPayVo);	
	StoreInsureInfo saveApplyPayment(StoreInsureInfo insureInfo,ResultVO result);
}
