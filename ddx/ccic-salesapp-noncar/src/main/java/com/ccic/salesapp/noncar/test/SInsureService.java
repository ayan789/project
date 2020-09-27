package com.ccic.salesapp.noncar.test;

import com.ccic.salesapp.noncar.dto.request.noncar.PlanElementDetaiReq;
import com.ccic.salesapp.noncar.dto.request.planelement.PlanElementDetailDto;
import com.ccic.salessapp.common.core.web.HttpResult;

public interface SInsureService {
    HttpResult<?> buildBackReasoningFactor(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq);
    HttpResult<?> buildHeader(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq);
    HttpResult<?> buildBottom(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq);
    HttpResult<?> buildCommonProblem(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq);
    HttpResult<?> buildFormulaList(PlanElementDetailDto planElementDetailDto, PlanElementDetaiReq planElementDetaiReq);
}
