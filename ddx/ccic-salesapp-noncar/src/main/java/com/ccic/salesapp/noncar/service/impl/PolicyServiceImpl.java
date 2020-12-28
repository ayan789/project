package com.ccic.salesapp.noncar.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.dto.NoncarOrder;
import com.ccic.salesapp.noncar.dto.StoreInsureInfo;
import com.ccic.salesapp.noncar.dto.request.PolicyStatusRequest;
import com.ccic.salesapp.noncar.dto.request.TNoncarInsureZcReqVO;
import com.ccic.salesapp.noncar.dto.request.UnderwriteStatusRequest;
import com.ccic.salesapp.noncar.dto.response.TNoncarInsureZcResVO;
import com.ccic.salesapp.noncar.repository.basedb.mapper.RenewalDatabusExpiredDataMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.StoreInsureInfoMapper;
import com.ccic.salesapp.noncar.repository.basedb.mapper.TNoncarInsureinfoZcMapper;
import com.ccic.salesapp.noncar.service.PolicyService;
import com.ccic.salessapp.common.utils.InsuranceApplicationStatus;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	StoreInsureInfoMapper storeInsureInfoMapper;

	@Autowired
	TNoncarInsureinfoZcMapper tNoncarInsureinfoZcMapper;
	
	@Autowired
	RenewalDatabusExpiredDataMapper renewalDatabusExpiredDataMapper;

	@Override
	public void synUnderwriteStatus(UnderwriteStatusRequest request) {

	}

	@Override
	public void synPolicyStatus(List<PolicyStatusRequest> requestList) {
		for (PolicyStatusRequest request : requestList) {
			try {
				StoreInsureInfo insureInfo = storeInsureInfoMapper.selectBySureInfoNo(request.getCertiNo());
				if (insureInfo != null) {
					insureInfo.setPolicyNo(request.getPolicyNo());
					if ("1".equals(request.getStatus())) {// 生效
						insureInfo.setStatus(InsuranceApplicationStatus.SHENGXIAO);
						insureInfo.setPolicyCreateDate(new Date());
						String orderId = insureInfo.getOrderNo();
						TNoncarInsureZcResVO res = tNoncarInsureinfoZcMapper.queryZc(orderId);
						if (res != null) {
							TNoncarInsureZcReqVO req = new TNoncarInsureZcReqVO();
							BeanUtils.copyProperties(res, req);
							req.setPaySuccessDate(new Date());
							req.setPayType(request.getPayWay());
							tNoncarInsureinfoZcMapper.updateZcInfo(req);
						}
					}
					storeInsureInfoMapper.doUpdate(insureInfo);
					Map map = new HashMap();
					map.put("newPolicyNo", insureInfo.getPolicyNo());
					map.put("policyNo", request.getPolicyNo());
					renewalDatabusExpiredDataMapper.updateNewPolicyNoByPolicyNo(map);
					map.put("chargeTime", request.getChargeTime());
					storeInsureInfoMapper.updatechargeTime(map);
				} else {
					// 动态表单
					NoncarOrder noncarOrder = storeInsureInfoMapper.selectNoncarOrderByInsureNoA(request.getCertiNo());
					if (noncarOrder != null) {
						noncarOrder.setPolicyNo(request.getPolicyNo());
						if ("1".equals(request.getStatus())) {// 生效
							noncarOrder.setOrderStatus(InsuranceApplicationStatus.SHENGXIAO);
							noncarOrder.setPolicyCreateDate(new Date());
							String orderId = noncarOrder.getOrderNo();
							NoncarOrder res = storeInsureInfoMapper.queryZcTwo(orderId);
							if (res != null) {
								NoncarOrder req = new NoncarOrder();
								BeanUtils.copyProperties(res, req);
//								String chargeTime = request.getChargeTime();
//								SimpleDateFormat slf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//								Date format = slf.parse(chargeTime);
								req.setPayDate(new Date());
								storeInsureInfoMapper.updateZcInfoTwo(req);
							}
						}
						storeInsureInfoMapper.doUpdateTwo(noncarOrder);
						Map map = new HashMap();
						map.put("newPolicyNo", noncarOrder.getPolicyNo());
						map.put("policyNo", request.getPolicyNo());
						renewalDatabusExpiredDataMapper.updateNewPolicyNoByPolicyNo(map);
						//更新组合单号的状态
						storeInsureInfoMapper.updateStatusOrderByRelationId(noncarOrder.getRelationOrderId());
					}
				}
			} catch (Exception e) {
				log.error("投保单状态异步更新处理异常", e);
			}
		}
	}
}
