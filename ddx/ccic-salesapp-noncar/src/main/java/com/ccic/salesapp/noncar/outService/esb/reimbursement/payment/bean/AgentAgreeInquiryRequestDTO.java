package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author xuanxuan.wang
 * @date 2020/3/5
 */
@ApiModel("销管代理人协议查询请求模型")
@Data
public class AgentAgreeInquiryRequestDTO extends RequestBody {

    /**
     * 01:查询代理人协议信息
     * 02:查询业务风险分类 （N码）
     * 03:查询代理人协议信息（模糊查询）
     */
    @ApiModelProperty("请求类型01:查询代理人协议信息 02:查询业务风险分类")
    private String requestType;

    @ApiModelProperty("签单日期 yyyy-mm-dd")
    private String inputDate;

    @ApiModelProperty("代理人代码")
    private String agentCode;

    @ApiModelProperty("归属机构")
    private String comCode;

    @ApiModelProperty("业务渠道")
    private String businessNature;

    @ApiModelProperty("险种")
    private String riskCode;

    @ApiModelProperty("代理人协议号")
    private String agreementNo;

    @ApiModelProperty("代理人协议名称")
    private String agreementName;

}
