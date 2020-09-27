package com.ccic.salesapp.noncar.feign.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("支付状态回写")
public class PayResultConfirmRequestVO {

    @ApiModelProperty("投保单号")
    private String applicationNo;

    @ApiModelProperty("支付状态标志 0-支付失败 1-支付成功")
    private String paySucceedFlag;
}