package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean;

import com.ccic.salessapp.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("代理人协议查询请求模型")
public class AgentQueryReqVO extends BaseRequest {
    /**
     * 01:查询代理人协议信息
     * 02:查询业务风险分类 （N码）
     * 03:查询代理人协议信息（模糊查询）
     */
    @ApiModelProperty("请求类型01:查询代理人协议信息 02:查询业务风险分类 必填")
    private String requestType;

    @ApiModelProperty("业务员代码")
    private String staffCode;

    @ApiModelProperty("签单日期 yyyy-mm-dd")
    private String inputDate;

    @ApiModelProperty("代理人代码 必填")
    private String agentCode;

    @ApiModelProperty("归属机构 requestType=01时必填")
    private String comCode;

    @ApiModelProperty("业务渠道 requestType=01时必填")
    private String businessSource;

    @ApiModelProperty("险种 requestType=01时必填")
    private String riskCode;

    @ApiModelProperty("代理人协议号")
    private String agreementNo;

    @ApiModelProperty("代理人协议名称")
    private String agreementName;


}
