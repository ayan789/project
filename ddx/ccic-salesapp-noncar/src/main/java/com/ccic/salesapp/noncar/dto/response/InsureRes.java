package com.ccic.salesapp.noncar.dto.response;

import lombok.Data;

@Data
public class InsureRes {
    
    private String salesPlanCode;
    private String polHolderInsuredRelaCode;
    private String childPlanCode;
    private String childPlanName = "";
    private String name;
    
}
