package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author xuanxuan.wang
 * @date 2020/3/5
 */
@Data
@ApiModel("销管代理人协议查询返回模型")
public class AgentAgreeInquiryResponseDTO {

    @ApiModelProperty("代理人信息")
    private SalesAgentMsgDto salesAgentMsgDto;

    @ApiModelProperty("协议信息")
    private SalesAgreementMsgDto[] salesAgreementMsgList;
}
