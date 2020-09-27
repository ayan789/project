package com.ccic.salesapp.noncar.dto;


import com.ccic.salesapp.noncar.feign.bean.MessageSendVO;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Data
public class MessageDto implements Serializable {

    @ApiModelProperty(value="员工编号",name="userCode",example="title")
    private String userCode;

    @ApiModelProperty(value="读取状态(0：未读  1：已读 9：删除)",name="status",example="title")
    private String status = "0";

    @ApiModelProperty(value="消息主体内容",name="message",example="title")
    private String message;

    @ApiModelProperty(value="消息类型",name="messageType",example="title")
    private String messageType;

    @ApiModelProperty(value="保单号",name="policyNo",example="title")
    private String policyNo;

    @ApiModelProperty(value="消息来源",name="source",example="title")
    private String source;

    private String extendMessage;

    public static List<MessageDto> parseMessageSend(List<MessageSendVO> messageSendVOS){
        if(CollectionUtils.isEmpty(messageSendVOS)){
            return Lists.newArrayList();
        }
        List<MessageDto> messageDtos = Lists.newArrayList();
        MessageDto messageDto;
        for(MessageSendVO messageSendVO : messageSendVOS) {
            messageDto = new MessageDto();
            BeanUtils.copyProperties(messageSendVO,messageDto);
            messageDto.setStatus(MessageStatusEnum.UN_READ.getKey());
            messageDtos.add(messageDto);
        }
         return messageDtos;
    }
}
