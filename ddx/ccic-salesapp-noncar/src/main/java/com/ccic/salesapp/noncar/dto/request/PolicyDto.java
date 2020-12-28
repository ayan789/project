package com.ccic.salesapp.noncar.dto.request;

import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;

import lombok.Data;

@Data
public class PolicyDto {
    PolicyVo requestBody;
    RequestHead requestHead;
}
