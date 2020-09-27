package com.ccic.salesapp.performance.service;
import org.springframework.stereotype.Service;

import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salesapp.performance.domain.vo.dto.QueryPolicyVo;
import com.ccic.salesapp.performance.domain.vo.dto.ResultVO;
import com.ccic.salesapp.performance.domain.vo.dto.StoreInsureInfo;
import com.ccic.salesapp.performance.domain.vo.request.BillPackToFKRequest;
import com.ccic.salesapp.performance.domain.vo.request.CommonAgentVO;
import com.ccic.salesapp.performance.domain.vo.request.FeePayDetailRequest;
import com.ccic.salesapp.performance.domain.vo.request.PageVO;
import com.ccic.salesapp.performance.domain.vo.request.PlanFeeDetailInquiryRequestVO;
import com.ccic.salesapp.performance.domain.vo.request.PlanFeeInquiryRequestVO;
import com.ccic.salesapp.performance.domain.vo.request.QueryWithdrawalVo;
import com.ccic.salesapp.performance.domain.vo.request.UpdateConfigRequest;



/**
 * 
 * 佣金、手续费提现
 * @author 王鹏
 *
 */



@Service
public interface CommonAgentService {

	//添加常用代理人
	HttpResult<?> addCommonAgent(CommonAgentVO commonAgentVO);

	//更新常用代理人信息
	HttpResult<?> updateCommonAgent(CommonAgentVO commonAgentVO);

	//代理人手续费查询
	HttpResult<?> planFeeInquiry(PlanFeeInquiryRequestVO planFeeInquiryRequestVO);

	//当日手续费明细查询
	HttpResult<?> planFeeDetailInquiry(PlanFeeDetailInquiryRequestVO planFeeDetailInquiryRequestVO);

	//代理人手续费提現
	HttpResult<?> billPackToFK(BillPackToFKRequest billPackToFKRequestVO);

	//提现页面初始化查询
	HttpResult<?> queryPolicyList(QueryPolicyVo queryPolicyVo);

	//修改提现设置
	HttpResult<?> updateConfig(UpdateConfigRequest updateConfigRequestVO);

	//查询用户的提现配置
	HttpResult<?> queryWithdrawalConfig(QueryWithdrawalVo queryWithdrawalVo);

	//查询提现明细记录
	HttpResult<?> feePayDetailQuery(FeePayDetailRequest feePayDetailRequestVO ) throws Exception;

	//代理人注册信息查询
	HttpResult<?> queryInviteUserDetail(PlanFeeInquiryRequestVO planFeeInquiryRequestVO);
	
	HttpResult<?> saveApplyPayment(StoreInsureInfo insureInfo,ResultVO result);
	
	//代理人手续费提現
	HttpResult<?> queryCashWithdrawal(PlanFeeInquiryRequestVO planFeeInquiryRequestVO);
	
	HttpResult<?> queryCashWithdrawalByAgentCode(PlanFeeInquiryRequestVO planFeeInquiryRequestVO);
	
}
