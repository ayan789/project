package com.ccic.salesapp.noncar.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.controller.PlanFormCtl;
import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementDetaiReq;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanElementDetailDto;
import com.ccic.salessapp.common.core.web.HttpResult;

@Service
public class SInsureServiceImpl implements SInsureService {
	
	@Autowired 
	PlanFormCtl planFormCtl;

	@Override
	public HttpResult<?> buildBackReasoningFactor(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
		return planFormCtl.buildBackReasoningFactor(planElementDetailDto, planElementDetaiReq);
	}

	@Override
	public HttpResult<?> buildHeader(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
		return planFormCtl.buildHeader(planElementDetailDto, planElementDetaiReq);
	}

	@Override
	public HttpResult<?> buildBottom(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
		return planFormCtl.buildBottom(planElementDetailDto, planElementDetaiReq);
	}

	@Override
	public HttpResult<?> buildCommonProblem(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
		return planFormCtl.buildCommonProblem(planElementDetailDto, planElementDetaiReq);
	}

	@Override
	public HttpResult<?> buildFormulaList(PlanElementDetailDto planElementDetailDto,
			PlanElementDetaiReq planElementDetaiReq) {
		return planFormCtl.buildFormulaList(planElementDetailDto, planElementDetaiReq);
	}

}
