package com.ccic.salesapp.noncar.dto.response;

import com.ccic.salesapp.noncar.dto.request.planelement.ClaimStatement;
import com.ccic.salesapp.noncar.dto.request.planelement.CommonProblem;
import com.ccic.salesapp.noncar.dto.request.planelement.Header;
import com.ccic.salesapp.noncar.dto.request.planelement.ProductInfo;

import lombok.Data;

@Data
public class PlanDetail2FamilyResp {
    
    private Header header = new Header();
    
    private ProductInfo productInfo = new ProductInfo();
    
    private ClaimStatement claimStatement = new ClaimStatement();
    
    private CommonProblem commonProblem = new CommonProblem();
}
