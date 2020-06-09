package com.example.tlyanprd.planelement;
import lombok.Data;


@Data
public class FormulaList {

	private int planId;
	private int strategyId;
    private String formulaCode ="";
    private String formulaName ="";
    private String edition ="";
    private int limQuantity;
    private String isHaveSheBao ="";
    private String isIssueAfterPay ="";
    private SafeguardPlan safeguardPlan = new SafeguardPlan();//保障计划
    private ProductInfo productInfo = new ProductInfo();//产品简介
    private SafeguardDetail safeguardDetail = new SafeguardDetail();//保障详情
    private ClaimStatement claimStatement = new ClaimStatement();//理赔说明
    private TermsAndConditions termsAndConditions = new TermsAndConditions();//条款须知
    private ProductPoster productPoster = new ProductPoster();//产品海报
    private boolean isNeedHealthNotice;
    private HealthNotice healthNotice = new HealthNotice();//健康须知
    
}