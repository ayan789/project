package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author xuanxuan.wang
 * @date 2020/3/5
 */
@ApiModel("代理人信息")
@Data
public class SalesAgentMsgDto {

    @ApiModelProperty("签单日期 yyyy-mm-dd")
    private String inputDate;

    @ApiModelProperty("代理人代码")
    private String agentCode;

    @ApiModelProperty("代理人名称")
    private String agentName;

    @ApiModelProperty("经办人2返回信息")
    private SalesShadowAgentMsgDto[] salesShadowAgentMsgList;

    @ApiModelProperty("代理人地址")
    private String addressName;

    @ApiModelProperty("代理人协议名称")
    private String agreementName;

    @ApiModelProperty("代理人协议号")
    private String agreementNo;

    @ApiModelProperty("业务渠道")
    private String businessNature;

    @ApiModelProperty("归属机构")
    private String comCode;

    private String errorMessage;

    @ApiModelProperty("展业证书编号")
    private String exhibitionNo;

    @ApiModelProperty("许可证号")
    private String permitNo;

    @ApiModelProperty("电话")
    private String phoneNumber;

    private String queryDate;

    private String requestCode;

    private String requestType;

    @ApiModelProperty("险种")
    private String riskCode;

    private String system;

    @ApiModelProperty("组织机构代码")
    private String orgCode;


}
