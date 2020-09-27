package com.ccic.salesapp.noncar.dto;

import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean.AgentAgreeInquiryResponseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//public class AgentQueryResVO {
//	private SalesMainMsgDto salesMainMsgDto;
//	private String comName;//��������
//
//	public SalesMainMsgDto getSalesMainMsgDto() {
//		return salesMainMsgDto;
//	}
//
//	public void setSalesMainMsgDto(SalesMainMsgDto salesMainMsgDto) {
//		this.salesMainMsgDto = salesMainMsgDto;
//	}
//
//	public String getComName() {
//		return comName;
//	}
//
//	public void setComName(String comName) {
//		this.comName = comName;
//	}
	
	
	/**
	 * 代理信息查询
	 * @author Administrator
	 *
	 */
	@Data
	@ApiModel("代理信息查询响应模型")
	public class AgentQueryResVO {
		@ApiModelProperty("代理人信息及协议相关信息")
		private AgentAgreeInquiryResponseDTO salesMainMsgDto;

	}

//}
