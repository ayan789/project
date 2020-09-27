package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xuanxuan.wang
 * @date 2020/3/5
 */
@ApiModel("业务风险分类信息")
@Data
public class SalesShadowAgentMsgDto {

    @ApiModelProperty("经办人2代码(代理人或无资质代理人）")
    private String shadowAgentCode;
    @ApiModelProperty("经办人2名称")
    private String shadowAgentName;
    @ApiModelProperty("经办人2代理类型")
    private String shadowAgentType;

}
