package com.ccic.salesapp.noncar.dto.request.planelement;
import lombok.Data;


public class FormulaList {

	private int planId;//方案标识
	private int strategyId;//销售产品标识
    private String formulaCode ="";//方案CODE
    private String prdPlanCategory;
    private String formulaName ="";//方案名称
    private double basePremium;//最低保费
    private String hasTrial="0";//是否存在试算要素 1:有 0:无
    private String edition ="";//版本名称
    private Integer insurantLowerlimit;//被保人数量下限
    private Integer insurantUpperlimit;//被保人数量上限
    private Integer copyMin;//最小份数
    private Integer copyMax;//最大份数
    private Integer isHaveSheBao;//是否社保
    private Integer insuredAgeLowerlimit;//被保险人年龄下限
    private Integer insuredAgeUpperlimit;//被保险人年龄上限
    private Integer insuredAgeLowertype;//被保险人年龄下限类型
    private Integer insuredAgeUppertype;//被保险人年龄上限类型
    private Integer allowPartnerBill;//是否允许伙伴出单(1:是 2:否 3:仅)
    private Integer giCoveragePeriod;//保险期限
    private Integer giPeriodType;//保险期间类型
    private Integer underwritingType;//核保类型
    private String riskCode ="";
    private String ifPeriodFixed ="";//是否固定保险期间
    private String fixCoveragePeriod =""; //固定保险期间单位
    private int fixCoverageYear;//固定保险期间期限
    private String insuredOccupationType ="";//被保险人职业类别
    private SafeguardPlan safeguardPlan = new SafeguardPlan();//保障计划
    private ProductInfo productInfo = new ProductInfo();//产品简介
    private SafeguardDetail safeguardDetail = new SafeguardDetail();//保障详情
    private ClaimStatement claimStatement = new ClaimStatement();//理赔说明
    private TermsAndConditions termsAndConditions = new TermsAndConditions();//条款须知
    private boolean isNeedHealthNotice;
    private HealthNotice healthNotice = new HealthNotice();//健康须知
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(int strategyId) {
		this.strategyId = strategyId;
	}
	public String getFormulaCode() {
		return formulaCode;
	}
	public void setFormulaCode(String formulaCode) {
		this.formulaCode = formulaCode;
	}
	public String getPrdPlanCategory() {
		return prdPlanCategory;
	}
	public void setPrdPlanCategory(String prdPlanCategory) {
		this.prdPlanCategory = prdPlanCategory;
	}
	public String getFormulaName() {
		return formulaName;
	}
	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}
	public double getBasePremium() {
		return basePremium;
	}
	public void setBasePremium(double basePremium) {
		this.basePremium = basePremium;
	}
	public String getHasTrial() {
		return hasTrial;
	}
	public void setHasTrial(String hasTrial) {
		this.hasTrial = hasTrial;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public Integer getInsurantLowerlimit() {
		return insurantLowerlimit;
	}
	public void setInsurantLowerlimit(Integer insurantLowerlimit) {
		this.insurantLowerlimit = insurantLowerlimit;
	}
	public Integer getInsurantUpperlimit() {
		return insurantUpperlimit;
	}
	public void setInsurantUpperlimit(Integer insurantUpperlimit) {
		this.insurantUpperlimit = insurantUpperlimit;
	}
	public Integer getCopyMin() {
		return copyMin;
	}
	public void setCopyMin(Integer copyMin) {
		this.copyMin = copyMin;
	}
	public Integer getCopyMax() {
		return copyMax;
	}
	public void setCopyMax(Integer copyMax) {
		this.copyMax = copyMax;
	}
	public Integer getIsHaveSheBao() {
		return isHaveSheBao;
	}
	public void setIsHaveSheBao(Integer isHaveSheBao) {
		this.isHaveSheBao = isHaveSheBao;
	}
	public Integer getInsuredAgeLowerlimit() {
		return insuredAgeLowerlimit;
	}
	public void setInsuredAgeLowerlimit(Integer insuredAgeLowerlimit) {
		this.insuredAgeLowerlimit = insuredAgeLowerlimit;
	}
	public Integer getInsuredAgeUpperlimit() {
		return insuredAgeUpperlimit;
	}
	public void setInsuredAgeUpperlimit(Integer insuredAgeUpperlimit) {
		this.insuredAgeUpperlimit = insuredAgeUpperlimit;
	}
	public Integer getInsuredAgeLowertype() {
		return insuredAgeLowertype;
	}
	public void setInsuredAgeLowertype(Integer insuredAgeLowertype) {
		this.insuredAgeLowertype = insuredAgeLowertype;
	}
	public Integer getInsuredAgeUppertype() {
		return insuredAgeUppertype;
	}
	public void setInsuredAgeUppertype(Integer insuredAgeUppertype) {
		this.insuredAgeUppertype = insuredAgeUppertype;
	}
	public Integer getAllowPartnerBill() {
		return allowPartnerBill;
	}
	public void setAllowPartnerBill(Integer allowPartnerBill) {
		this.allowPartnerBill = allowPartnerBill;
	}
	public Integer getGiCoveragePeriod() {
		return giCoveragePeriod;
	}
	public void setGiCoveragePeriod(Integer giCoveragePeriod) {
		this.giCoveragePeriod = giCoveragePeriod;
	}
	public Integer getGiPeriodType() {
		return giPeriodType;
	}
	public void setGiPeriodType(Integer giPeriodType) {
		this.giPeriodType = giPeriodType;
	}
	public Integer getUnderwritingType() {
		return underwritingType;
	}
	public void setUnderwritingType(Integer underwritingType) {
		this.underwritingType = underwritingType;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getIfPeriodFixed() {
		return ifPeriodFixed;
	}
	public void setIfPeriodFixed(String ifPeriodFixed) {
		this.ifPeriodFixed = ifPeriodFixed;
	}
	public String getFixCoveragePeriod() {
		return fixCoveragePeriod;
	}
	public void setFixCoveragePeriod(String fixCoveragePeriod) {
		this.fixCoveragePeriod = fixCoveragePeriod;
	}
	public int getFixCoverageYear() {
		return fixCoverageYear;
	}
	public void setFixCoverageYear(int fixCoverageYear) {
		this.fixCoverageYear = fixCoverageYear;
	}
	public String getInsuredOccupationType() {
		return insuredOccupationType;
	}
	public void setInsuredOccupationType(String insuredOccupationType) {
		this.insuredOccupationType = insuredOccupationType;
	}
	public SafeguardPlan getSafeguardPlan() {
		return safeguardPlan;
	}
	public void setSafeguardPlan(SafeguardPlan safeguardPlan) {
		this.safeguardPlan = safeguardPlan;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	public SafeguardDetail getSafeguardDetail() {
		return safeguardDetail;
	}
	public void setSafeguardDetail(SafeguardDetail safeguardDetail) {
		this.safeguardDetail = safeguardDetail;
	}
	public ClaimStatement getClaimStatement() {
		return claimStatement;
	}
	public void setClaimStatement(ClaimStatement claimStatement) {
		this.claimStatement = claimStatement;
	}
	public TermsAndConditions getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(TermsAndConditions termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	public boolean getIsNeedHealthNotice() {
		return isNeedHealthNotice;
	}
	public void setIsNeedHealthNotice(boolean isNeedHealthNotice) {
		this.isNeedHealthNotice = isNeedHealthNotice;
	}
	public HealthNotice getHealthNotice() {
		return healthNotice;
	}
	public void setHealthNotice(HealthNotice healthNotice) {
		this.healthNotice = healthNotice;
	}
    
    
    
}