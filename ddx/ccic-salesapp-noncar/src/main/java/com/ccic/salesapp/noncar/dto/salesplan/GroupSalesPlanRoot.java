/**
  * Copyright 2020 bejson.com 
  */
package com.ccic.salesapp.noncar.dto.salesplan;
import java.util.List;

import com.ccic.salesapp.noncar.dto.AgentInfoVO;
import com.ccic.salesapp.noncar.dto.order.request.GroupOrderRequest;
import com.ccic.salesapp.noncar.dto.order.response.GroupOrderDetail;
import com.ccic.salesapp.noncar.dto.order.response.OrderDetailResponse;
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
	@ApiModelProperty("组合方案基本信息")
    private SaleInfo saleInfo;
	@ApiModelProperty("组合方案详细信息")
    private List<GroupSalesPlan> groupSalesPlan;
	private GroupOrderDetail groupOrderDetail;
	private AgentInfoVO agentInfo;
	private InvoiceInfo invoiceInfo;
	private Object userInfo;
	private String token;
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
	
	
     
     
}