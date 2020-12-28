package com.ccic.salesapp.noncar.repository.basedb.po;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class SalesCoverage {
    private Integer coverageId;

    private String coverageCode;

    private String coverageName;

    private String clauseCode;

    private String clauseName;

    private Integer childPlanId;

    private BigDecimal sumInsured;

    private BigDecimal baseSumInsured;

    private String sumInsuredFields;

    private String insuredType;

    private String insuredName;

    private String mainCt;

    private String riskType;

    private String insuranceType;
    
    private String ctGroupType;
}