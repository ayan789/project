package com.ccic.salesapp.noncar.dto.response.policyInquiry.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.alibaba.fastjson.JSON;
import com.ccic.salesapp.noncar.dto.response.policyInquiry.PolicyInquiryRequest;
import com.ccic.salesapp.noncar.dto.response.policyInquiry.PolicyInquiryResponse;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.RequestHead;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;
import com.ccic.salessapp.common.outService.rest.common.bean.RestfulResponse;
import com.ccic.salessapp.common.utils.ValidationUtils;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PolicyInquiryService {
//    @Value("${sonic.platform.interface.policyInfoSearchUrl}")
//    String policyInfoSearch;
//    public PolicyInquiryResponse policyInfoRequest(String policyNo){
//        Request policyInquiryRequest = new Request();
//        RequestHead policyInquiryRequestHead = RequestHead.getDefault();
//        policyInquiryRequest.setRequestHead(policyInquiryRequestHead);
//        PolicyInquiryRequest requestBody = new PolicyInquiryRequest();
//        requestBody.setPolicyNo(policyNo);
//        requestBody.setQueryType("4");//车险业务
//        policyInquiryRequest.setRequestBody(requestBody);
//        String reqJson = JSON.toJSONString(policyInquiryRequest);
//        log.info("续保获取保单详情开始，响应报文：" + reqJson);
//        RestfulResponse<PolicyInquiryResponse> resp = Unirest.post(policyInfoSearch)
//                .body(policyInquiryRequest)
//                .asObject(new GenericType<RestfulResponse<PolicyInquiryResponse>>() {
//                }).getBody();
//        String resJson = JSON.toJSONString(resp);
//        log.info("续保获取保单详情结束，响应报文：" + resJson);
//        ResponseHead responseHead = resp.getResponseHead();
//        ValidationUtils.isTrue(responseHead.getStatus() == 0, String.format("保单信息获取失败.服务提供方信息：%s-%s]",
//                responseHead.getAppCode(),responseHead.getAppMessage()));
//        if (resp == null) {
//        	return null;
//        }else{
//            return resp.getResponseBody();
//        }
//    }
}
