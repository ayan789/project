package com.ccic.salesapp.performance.domain.vo.request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ccic.salesapp.performance.domain.vo.dto.DataVO;



@ApiModel("代理人今日手续费查询request")
public class PlanFeeDetailInquiryRequestVO extends DataVO {	
	
	@ApiModelProperty("代理人code")
	private String desagentCode;
	
	private PageVO page;
	
	@ApiModelProperty("人员工号")
	private String userCode;
	
	private String agentCode;
	
	private String handlerCode;

	public String getDesagentCode() {
		return desagentCode;
	}

	public void setDesagentCode(String desagentCode) {
		this.desagentCode = desagentCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public PageVO getPage() {
		return page;
	}

	public void setPage(PageVO page) {
		this.page = page;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getHandlerCode() {
		return handlerCode;
	}

	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}

	
	
	
	
}
