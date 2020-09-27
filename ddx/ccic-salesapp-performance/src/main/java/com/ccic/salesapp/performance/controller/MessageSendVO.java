package com.ccic.salesapp.performance.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MessageSendVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="员工编号",name="userCode",example="100001")
    private String userCode;

    @ApiModelProperty(value="消息主体内容",name="message",example="")
    private String message;

    @ApiModelProperty(value="消息类型",name="messageType",example="cust-pay-suceed")
    private String messageType;

    @ApiModelProperty(value="消息来源(0-android,1-ios,3-webchat,4-web,5-other)",name="source",example="0")
    private String source = "5";

    @ApiModelProperty(value="扩展字段(json)",name="extendMessage",example="")
    private String extendMessage;

    @ApiModelProperty(value="业务编码",name="bizNo",example="P122323")
    private String bizNo;

    @ApiModelProperty(value="消息标题",name="title",example="超级APP尊敬的客户")
    private String title;

    @ApiModelProperty(value="消息副标题",name="subTitle",example="最新特别消息")
    private String subTitle;

    @ApiModelProperty(value="推送账号数组",name="userCodes",example="")
    private List<String> userCodes;
}
