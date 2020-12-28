package com.ccic.salesapp.noncar.service;

import com.ccic.salesapp.noncar.dto.renewal.request.ClaimQueryRequest;
import com.ccic.salesapp.noncar.dto.renewal.request.RenewalRequest;
import com.ccic.salesapp.noncar.dto.renewal.response.ClaimQueryResponse;
import com.ccic.salesapp.noncar.dto.renewal.response.RenewalStatusQueryResponseDTO;

public interface RenewalService {

	RenewalStatusQueryResponseDTO healthRenewalStatusQuery(RenewalRequest request);

	ClaimQueryResponse claimQuery(ClaimQueryRequest request);

}
