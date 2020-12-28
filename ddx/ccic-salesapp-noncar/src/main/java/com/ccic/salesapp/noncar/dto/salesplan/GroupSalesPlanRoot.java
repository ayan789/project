/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.salesplan;
import java.util.List;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.order.request.GroupOrderRequest;
import com.ccic.salesapp.noncar.dto.order.response.GroupOrderDetail;
import com.ccic.salesapp.noncar.dto.order.response.OrderDetailResponse;
import com.ccic.salesapp.noncar.dto.request.planelement.ClaimStatement;
import com.ccic.salesapp.noncar.dto.request.planelement.CommonProblem;
import com.ccic.salesapp.noncar.dto.request.planelement.Header;
import com.ccic.salesapp.noncar.dto.request.planelement.HealthNotice;
import com.ccic.salesapp.noncar.dto.request.planelement.ProductInfo;
import com.ccic.salesapp.noncar.dto.request.planelement.SafeguardDetail;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditions;
import com.ccic.salesapp.noncar.dto.request.planelement.TermsAndConditionsContent;
import com.ccic.salesapp.noncar.dto.InvoiceInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Auto-generated: 2020-09-09 14:2:59
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@ApiModel("组合方案")
public class GroupSalesPlanRoot {
	private Long salesPlanId;
	@ApiModelProperty("组合方案基本信息")
    private SaleInfo saleInfo;
	private Header header;
	@ApiModelProperty("组合方案详细信息")
    private List<GroupSalesPlan> groupSalesPlan;
	private GroupOrderDetail groupOrderDetail;
	private AgentInfoVO agentInfo;
	private InvoiceInfo invoiceInfo;
	private Object userInfo;
	private String token;
	@ApiModelProperty("常见问题")
	private CommonProblem commonProblem = new CommonProblem();
	@ApiModelProperty("产品简介")
	private ProductInfo productInfo = new ProductInfo();
//    @ApiModelProperty("保障详情")
//    private SafeguardDetail safeguardDetail = new SafeguardDetail();
    @ApiModelProperty("理赔说明")
    private ClaimStatement claimStatement = new ClaimStatement();
    @ApiModelProperty("条款须知")
    private TermsAndConditions termsAndConditions = new TermsAndConditions();
    private boolean isNeedHealthNotice;
    @ApiModelProperty("健康须知")
    private HealthNotice healthNotice = new HealthNotice();
    public void setSaleInfo(SaleInfo saleInfo) {
         this.saleInfo = saleInfo;
     }
     public SaleInfo getSaleInfo() {
         return saleInfo;
     }

    public void setGroupSalesPlan(List<GroupSalesPlan> groupSalesPlan) {
         this.groupSalesPlan = groupSalesPlan;
     }
     public List<GroupSalesPlan> getGroupSalesPlan() {
         return groupSalesPlan;
     }
	public AgentInfoVO getAgentInfo() {
		return agentInfo;
	}
	public void setAgentInfo(AgentInfoVO agentInfo) {
		this.agentInfo = agentInfo;
	}
	public InvoiceInfo getInvoiceInfo() {
		return invoiceInfo;
	}
	public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
		this.invoiceInfo = invoiceInfo;
	}
	public GroupOrderDetail getGroupOrderDetail() {
		return groupOrderDetail;
	}
	public void setGroupOrderDetail(GroupOrderDetail groupOrderDetail) {
		this.groupOrderDetail = groupOrderDetail;
	}
	public Object getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(Object userInfo) {
		this.userInfo = userInfo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
//	public SafeguardDetail getSafeguardDetail() {
//		return safeguardDetail;
//	}
//	public void setSafeguardDetail(SafeguardDetail safeguardDetail) {
//		this.safeguardDetail = safeguardDetail;
//	}
	public ClaimStatement getClaimStatement() {
		return claimStatement;
	}
	public void setClaimStatement(ClaimStatement claimStatement) {
		this.claimStatement = claimStatement;
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
	public void setNeedHealthNotice(boolean isNeedHealthNotice) {
		this.isNeedHealthNotice = isNeedHealthNotice;
	}
	public CommonProblem getCommonProblem() {
		return commonProblem;
	}
	public void setCommonProblem(CommonProblem commonProblem) {
		this.commonProblem = commonProblem;
	}
	public Long getSalesPlanId() {
		return salesPlanId;
	}
	public void setSalesPlanId(Long salesPlanId) {
		this.salesPlanId = salesPlanId;
	}
	public TermsAndConditions getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(TermsAndConditions termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	
	

	
	
     
     
}