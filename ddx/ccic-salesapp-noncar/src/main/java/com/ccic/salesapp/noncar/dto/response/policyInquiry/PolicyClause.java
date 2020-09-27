package com.ccic.salesapp.noncar.dto.response.policyInquiry;

import lombok.Data;

import java.util.List;
@Data
public class PolicyClause {
    private String clauseName;	//条款名称
    private List<PolicyCoverage> policyCoverages;//	保单险种列表
}

