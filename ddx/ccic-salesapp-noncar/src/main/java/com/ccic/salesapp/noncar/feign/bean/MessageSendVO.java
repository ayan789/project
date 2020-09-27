package com.ccic.salesapp.noncar.feign.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MessageSendVO implements Serializable {
    @ApiModelProperty(value="员工编号",name="userCode",example="100001")
    private String userCode;

    @ApiModelProperty(value="消息主体内容",name="message",example="非车险出单:【车牌号】沪L99999支付已成功，请尽快确认。")
    private String message;

    @ApiModelProperty(value="消息类型",name="messageType",example="cust-pay-suceed")
    private String messageType;

    @ApiModelProperty(value="消息来源(0-android,1-ios,3-webchat,4-web,5-other)",name="source",example="0")
    private String source = "5";

    @ApiModelProperty(value="扩展字段(json)",name="extendMessage",example="")
    private String extendMessage;

    @ApiModelProperty(value="业务编码",name="bizNo",example="P122323")
    private String bizNo;

    @ApiModelProperty(value="客户ID",name="consumerID",example="supsale")
    private String consumerID = "supsale";

    @ApiModelProperty(value="提供者ID",name="consumerID",example="custApp")
    private String providerID = "custApp";

    @ApiModelProperty(value="消息版本",name="version",example="1.0")
    private String version = "1.0.0";

    @ApiModelProperty(value="APP平台标识",name="platform",example="supsale")
    private String platform = "supsale";

    @ApiModelProperty(value="是否发送全部手机(0-全部，1-指定手机用户)",name="isAll",example="0")
    private int isAll = 0;

    @ApiModelProperty(value="消息标题",name="title",example="超级APP尊敬的客户")
    private String title;

    @ApiModelProperty(value="消息副标题",name="subTitle",example="最新特别消息")
    private String subTitle;

    @ApiModelProperty(value="推送账号数组",name="userCodes",example="['1233','32343']")
    private List<String> userCodes;
}
