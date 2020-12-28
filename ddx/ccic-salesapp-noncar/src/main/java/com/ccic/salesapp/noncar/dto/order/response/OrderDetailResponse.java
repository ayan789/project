package com.ccic.salesapp.noncar.dto.order.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.InvoiceInfo;
import com.ccic.salesapp.noncar.dto.request.planelement.HealthNotice;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;
import com.ccic.salesapp.noncar.repository.basedb.po.Order;
import com.ccic.salesapp.noncar.repository.basedb.po.OrderCt;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

public class OrderDetailResponse {

	@ApiModelProperty("元素分组列表")
	List<ElementGroup> elementList;
	
	@ApiModelProperty("产品名称")
	String productName;
	
	@ApiModelProperty("开始时间")
	String startDate;
	
	@ApiModelProperty("结束时间")
	String endDate;
	
	@ApiModelProperty("保障年限")
	String timeLimit;
	
    @ApiModelProperty("0:无发票信息,1:个人，2：企业")
    String invoiceFlag;
	
	@ApiModelProperty("开票信息")
	InvoiceInfo invoiceInfo = new InvoiceInfo();
	
	@ApiModelProperty("方案险类")
	String planCategory;
	
	@ApiModelProperty("门店代码")
	String storeCode;
	
	@ApiModelProperty("用户代码")
	String userCode;
	
	@ApiModelProperty("代理信息")
	AgentInfoVO agentInfo = new AgentInfoVO();
	
	@ApiModelProperty("渠道代码")
	String businessNatureCode;
	@ApiModelProperty("渠道名称")
	String  businessNatureName;
	
	@ApiModelProperty("投保单号")
	String insureNo;
	
	@ApiModelProperty("是否代理人")
	String isAgent;
	
	@ApiModelProperty("保额")
	BigDecimal sumAmount;
	
	@ApiModelProperty("应收保费")
	BigDecimal sumPremium;
	
	@ApiModelProperty("方案代码")
	String formulaCode;
	
	@ApiModelProperty("产品代码")
	String riskCode;
	
	@ApiModelProperty("订单编号")
	String orderNo;
	
	@ApiModelProperty("产品id")
	Long strategyId;
	
	@ApiModelProperty("方案编号")
	Long planId;
	
	@ApiModelProperty("方案份数")
	int formulaSize;// 方案份数
	
	@ApiModelProperty("是否暂存  1:暂存；0：提交核心")
	String isTemp;
	
	@ApiModelProperty("已选产品")
	List<OrderCt> productList;
	
	@ApiModelProperty("订单")
	Order order;
	
	@ApiModelProperty("未上传的影像列表")
	List<String> notUploadImgList;
	
	@ApiModelProperty("责任条款信息")
	TermsAndConditions termsAndConditions;
	
	@ApiModelProperty("订单过期剩余毫秒数")
	Long intervalDate;
	
	@ApiModelProperty("上架方案数量")
	private Integer onlinePlanNum;
	
	@ApiModelProperty("非接口参数,上下文参数")
	Map<String,Object> map;
	
	@ApiModelProperty("健康须知标识")
	private boolean isNeedHealthNotice;
	 
    @ApiModelProperty("健康须知")
    private HealthNotice healthNotice = new HealthNotice();

    public List<ElementGroup> getElementList() {
        return elementList;
    }

    public void setElementList(List<ElementGroup> elementList) {
        this.elementList = elementList;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(String invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public String getPlanCategory() {
        return planCategory;
    }

    public void setPlanCategory(String planCategory) {
        this.planCategory = planCategory;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public AgentInfoVO getAgentInfo() {
        return agentInfo;
    }

    public void setAgentInfo(AgentInfoVO agentInfo) {
        this.agentInfo = agentInfo;
    }

    public String getBusinessNatureCode() {
        return businessNatureCode;
    }

    public void setBusinessNatureCode(String businessNatureCode) {
        this.businessNatureCode = businessNatureCode;
    }

    public String getBusinessNatureName() {
        return businessNatureName;
    }

    public void setBusinessNatureName(String businessNatureName) {
        this.businessNatureName = businessNatureName;
    }

    public String getInsureNo() {
        return insureNo;
    }

    public void setInsureNo(String insureNo) {
        this.insureNo = insureNo;
    }

    public String getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(String isAgent) {
        this.isAgent = isAgent;
    }

    public BigDecimal getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(BigDecimal sumAmount) {
        this.sumAmount = sumAmount;
    }

    public BigDecimal getSumPremium() {
        return sumPremium;
    }

    public void setSumPremium(BigDecimal sumPremium) {
        this.sumPremium = sumPremium;
    }

    public String getFormulaCode() {
        return formulaCode;
    }

    public void setFormulaCode(String formulaCode) {
        this.formulaCode = formulaCode;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public int getFormulaSize() {
        return formulaSize;
    }

    public void setFormulaSize(int formulaSize) {
        this.formulaSize = formulaSize;
    }

    public String getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(String isTemp) {
        this.isTemp = isTemp;
    }

    public List<OrderCt> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderCt> productList) {
        this.productList = productList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<String> getNotUploadImgList() {
        return notUploadImgList;
    }

    public void setNotUploadImgList(List<String> notUploadImgList) {
        this.notUploadImgList = notUploadImgList;
    }

    public TermsAndConditions getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(TermsAndConditions termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public Long getIntervalDate() {
        return intervalDate;
    }

    public void setIntervalDate(Long intervalDate) {
        this.intervalDate = intervalDate;
    }

    public Integer getOnlinePlanNum() {
        return onlinePlanNum;
    }

    public void setOnlinePlanNum(Integer onlinePlanNum) {
        this.onlinePlanNum = onlinePlanNum;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
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
