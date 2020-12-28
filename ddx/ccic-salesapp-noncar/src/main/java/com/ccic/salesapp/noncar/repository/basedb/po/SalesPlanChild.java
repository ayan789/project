package com.ccic.salesapp.noncar.repository.basedb.po;

import lombok.Data;

@Data
public class SalesPlanChild {
    private Integer childPlanId;

    private String childPlanCode;

    private String childPlanName;

    private String productCode;

    private String productName;

    private Integer productLine;

    private String insuranceType;

    private String masterProduct;

    private String isUsed;

    private Integer salesPlanId;

    private String policyform;
    
    private Integer secondLine;

   
}