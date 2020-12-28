package com.ccic.salesapp.noncar.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccic.salesapp.noncar.dto.RecommendRankPlansVo;
import com.ccic.salesapp.noncar.dto.order.request.OrderDetailRequest;
import com.ccic.salesapp.noncar.dto.request.ApplyPayVo;
import com.ccic.salesapp.noncar.dto.request.ForwardPayInsureInfoReqVO;
import com.ccic.salesapp.noncar.dto.request.GetPayUrlRequestVO;
import com.ccic.salesapp.noncar.dto.request.PolicyVo;
import com.ccic.salesapp.noncar.dto.salesplan.GroupPlanVo;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface GroupPlanService {
	//组合方案同步	
	void groupPlanSync(HttpServletRequest request,HttpServletResponse response) throws IOException;
	HttpResult groupPlanList(HashMap<String,Object> map);
	//组合方案详情
	HttpResult groupPlanDetail(GroupPlanVo groupPlanVo,HttpServletRequest request);
	HttpResult planDetail2CarXY(GroupPlanVo groupPlanVo,HttpServletRequest request);
	//暂存和复制投保单
	HttpResult stagCopyInsure(OrderDetailRequest request);
	//signInit
	HttpResult signInit(ForwardPayInsureInfoReqVO forwardPayInsureInfo);
	//申请支付号
	HttpResult applyPayment(ApplyPayVo applyPayVo);
	//生成支付链接
	HttpResult genePaymentLink(GetPayUrlRequestVO request);
	//支付同步通知
	HttpResult paySyncNotice(ApplyPayVo applyPayVo) throws Exception;
	//保单列表
	//HttpResult policyList(HashMap<String,Object> map);
	//家庭版推荐方案
	HttpResult recommendRankPlans(RecommendRankPlansVo recommendRankPlansVo);
	//家庭版初始化信息
	HttpResult planDetail2Family(GroupPlanVo groupPlanVo,HttpServletRequest request);
	//保单列表(联合保单)
	HttpResult policyList(PolicyVo policyVo);
	//保单列表(保单&保单三要素)
	HttpResult policyListStaffOnly(String uuid,PolicyVo policyVo);

}
