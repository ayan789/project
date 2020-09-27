package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.service;

import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.bean.AgentAgreeInquiryResponseDTO;
import com.ccic.salessapp.common.outService.rest.common.bean.Request;
import com.ccic.salessapp.common.outService.rest.common.bean.ResponseHead;
import com.ccic.salessapp.common.outService.rest.common.bean.RestfulResponse;
import com.ccic.salessapp.common.utils.ValidationUtils;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 销管服务
 *
 * @date 2020/3/5
 */
@Slf4j
@Service
public class ReimbursementOutService {

    @Value("${esb.address.agentQueryUrl}")
    String agentQueryUrl;

    /**
     * 查询代理协议信息
     * @param request
     * @return
     */
    public AgentAgreeInquiryResponseDTO agentQuery(Request request) {
        log.info("esb接口代理信息查询开始.");
        RestfulResponse<AgentAgreeInquiryResponseDTO> responseESB = Unirest.post(agentQueryUrl).body(request).asObject(new GenericType<RestfulResponse<AgentAgreeInquiryResponseDTO>>() {}).getBody();
        log.info("esb接口代理信息查询结束.");
        if (responseESB != null) {
            ResponseHead responseHead = responseESB.getResponseHead();
            if (responseHead != null) {
                int resState = responseHead.getStatus();
                log.info("返回状态：" + resState);
                log.info("appMessage：" + responseHead.getAppMessage());
                log.info("ESBMessage：" + responseHead.getEsbMessage());
                ValidationUtils.isTrue(responseHead.getStatus() == 0, String.format("代理信息查询失败.服务提供方信息：%s-%s]",responseHead.getAppCode(), responseHead.getAppMessage()));
            }
            AgentAgreeInquiryResponseDTO agentAgreeInquiryResponseDTO = responseESB.getResponseBody();
            return agentAgreeInquiryResponseDTO;
        }else {
        	return null;
        }
    }
}
