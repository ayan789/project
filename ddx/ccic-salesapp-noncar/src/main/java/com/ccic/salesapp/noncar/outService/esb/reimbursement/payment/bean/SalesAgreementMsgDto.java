
package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SalesAgreementMsgDto implements java.io.Serializable {

    @ApiModelProperty("交强险最高手续费比例")
    private double TCIDefaultTopcommission;

    @ApiModelProperty("交强险默认手续费比例")
    private double TCITopcommission;

    @ApiModelProperty("代理人协议名称")
    private String agreementName;

    @ApiModelProperty("代理人协议号")
    private String agreementNo;

    @ApiModelProperty("归属机构")
    private String comCode;

    @ApiModelProperty("默认最高手续费比例")
    private double defaultTopcommission;

    @ApiModelProperty("终止日期")
    private String endDate;

    @ApiModelProperty("起始日期")
    private String startDate;

    @ApiModelProperty("最高手续费比例")
    private double topcommission;

    @ApiModelProperty("险种")
    private String riskCode;

}
