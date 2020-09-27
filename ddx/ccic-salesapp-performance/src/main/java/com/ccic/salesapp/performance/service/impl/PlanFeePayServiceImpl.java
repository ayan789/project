package com.ccic.salesapp.performance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccic.salesapp.performance.domain.vo.dto.PlanFeePay;
import com.ccic.salesapp.performance.repository.basedb.mapper.PlanFeePayMapper;
import com.ccic.salesapp.performance.service.PlanFeePayService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanFeePayServiceImpl implements PlanFeePayService {

	@Autowired
	PlanFeePayMapper planFeePayMapper;
	
	@Override
	@Transactional
	public void updatePlanFee(List<PlanFeePay> list) {
		log.info("收到手續費更新通知：共計" + list.size()+"條數據");
		for (PlanFeePay planFeePay : list) {
			planFeePayMapper.updatePlanFee(planFeePay);
			String action = "";
			if(planFeePay.getCount() > 0) {
				action = "更新";
			}else {
				action = "插入";
			}
			log.info("执行动作："+action);
			log.info(action+"手續費信息：certiId：" + planFeePay.getCertiId()+"已完成");
		}
		log.info("已完成：手續費信息处理：共計" + list.size()+"條數據");
	}
	
}
