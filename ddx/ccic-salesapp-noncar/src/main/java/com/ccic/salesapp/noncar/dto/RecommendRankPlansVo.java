package com.ccic.salesapp.noncar.dto;
import java.util.List;

import lombok.Data;

@Data
public class RecommendRankPlansVo {

    private String groupPlanCode;
    private String salesPlanCode;
    private int requestType;
    private String orgCode;
    private String channelByPerson;
    private List<PolicyCustomerList> policyCustomerList;
    private String orderId;
   
}