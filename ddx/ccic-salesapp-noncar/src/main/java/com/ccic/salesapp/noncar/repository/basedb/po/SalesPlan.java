package com.ccic.salesapp.noncar.repository.basedb.po;

import java.util.Date;

import lombok.Data;

@Data
public class SalesPlan {
    private Integer salesPlanId;

    private String salesPlanCode;

    private String salesPlanName;

    private String salesType;

    private String groupPlanCode;

    private String groupPlanName;

    private String status;

    private String orgCode;

    private String channelByPerson;

    private Date effectiveDate;

    private Date expiryDate;
    
    private String smsTemplateId;
    
    private String salesPlanType;
    
    private String groupCode;
    
    private String groupName;
    
    private String discountType;
   
}