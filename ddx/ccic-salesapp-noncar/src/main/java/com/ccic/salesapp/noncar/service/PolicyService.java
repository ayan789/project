package com.ccic.salesapp.noncar.service;

import java.util.List;

import com.ccic.salesapp.noncar.dto.request.PolicyStatusRequest;
import com.ccic.salesapp.noncar.dto.request.UnderwriteStatusRequest;

public interface PolicyService {

	void synUnderwriteStatus(UnderwriteStatusRequest request);

	void synPolicyStatus(List<PolicyStatusRequest> request);
//	void synPolicyStatusTwo(List<PolicyStatusRequest> request);
//	void synUnderwriteStatusTwo(UnderwriteStatusRequest request);
}
