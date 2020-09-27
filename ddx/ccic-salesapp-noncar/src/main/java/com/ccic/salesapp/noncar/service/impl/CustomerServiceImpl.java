package com.ccic.salesapp.noncar.service.impl;

import com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.bean.*;
import com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.intf.EntBaseInfoInquiry;
import com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.intf.EntBaseInfoInquiryService;
import com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.bean.PerBaseInfoInquiryRequest;
import com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.bean.PerBaseInfoInquiryRequestDTO;
import com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.bean.PerBaseInfoInquiryResponse;
import com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.bean.PerCustBaseInfoDTO;
import com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.intf.PerBaseInfoInquiry;
import com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.intf.PerBaseInfoInquiryService;
import com.ccic.salesapp.noncar.repository.basedb.po.AppCustomer;
import com.ccic.salesapp.noncar.service.CustomerService;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    PerBaseInfoInquiry perBaseInfoInquiry;
    @Autowired
    EntBaseInfoInquiry entBaseInfoInquiry;

    public static RequestHeadDTO getRequestHeadDTO(){
        RequestHeadDTO requestHead = new RequestHeadDTO();
        requestHead.setConsumerID("supsale");
        return requestHead;
    }
    @Override
    public AppCustomer findCustomer(String custNo, String custType) {
        AppCustomer appCustomer1 = new AppCustomer();

        if ("1".equals(custType)) {
            //使用过滤出的投保人列表查询客户信息,调webservice接口
            PerCustBaseInfoDTO perCustBaseInfoDTO = doPerBaseInfoInquiryRequest(custNo);
            if (Objects.nonNull(perCustBaseInfoDTO)) {
               // BeanUtils.copyProperties(perCustBaseInfoDTO, appCustomer1);
                appCustomer1.setCustNo(custNo);
                appCustomer1.setCertificateType(perCustBaseInfoDTO.getIdentifyType());
                appCustomer1.setCertificateNo(perCustBaseInfoDTO.getIdentifyNumber());
                appCustomer1.setCustType(custType);
                return appCustomer1;
            }
        } else {
            EntCustBaseInfoDTO entCustBaseInfoDTO = doEntBaseInfoInquiryRequest(custNo);
            if (Objects.nonNull(entCustBaseInfoDTO)) {
               // BeanUtils.copyProperties(entCustBaseInfoDTO, appCustomer1);
                appCustomer1.setCustNo(custNo);
                appCustomer1.setCertificateNo(entCustBaseInfoDTO.getIdentifyNumber());
                appCustomer1.setCustType(custType);
                return appCustomer1;
            }
        }
        return null;
    }


    /**
     * @MathodName doPerBaseInfoInquiryRequest
     * @Description 调ECIF查个人客户基本信息     * @Author jiangying
     * @Params [custNo]
     *
     * * @Returns com.ccic.salesapp.customer.outService.esb.ecif.perBaseInfoInquiry.bean.PerCustBaseInfoDTO
     * @Data 2020/2/29
     */
    public  PerCustBaseInfoDTO doPerBaseInfoInquiryRequest(String custNo){

        /*PerBaseInfoInquiryService perBaseInfoInquiryService = new PerBaseInfoInquiryService();
        PerBaseInfoInquiry perBaseInfoInquiry = perBaseInfoInquiryService.getPerBaseInfoInquiryEndpoint()*/;

        //请求实体
        PerBaseInfoInquiryRequest perBaseInfoInquiryRequest = new PerBaseInfoInquiryRequest();
        //请求头
        RequestHeadDTO requestHeadDTO = getRequestHeadDTO();
        perBaseInfoInquiryRequest.setRequestHead(requestHeadDTO);
        //请求体
        PerBaseInfoInquiryRequestDTO perBaseInfoInquiryRequestDTO = new PerBaseInfoInquiryRequestDTO();
        //客户号
        try {
            perBaseInfoInquiryRequestDTO.setCustNo(Long.parseLong(custNo));
        }catch (NumberFormatException e){
            log.error(custNo);
            e.printStackTrace();
        }
        //角色
        perBaseInfoInquiryRequestDTO.setRole("1001");
        perBaseInfoInquiryRequest.setRequestBody(perBaseInfoInquiryRequestDTO);

        //发起请求
        PerBaseInfoInquiryResponse perBaseInfoInquiryResponse = perBaseInfoInquiry.process(perBaseInfoInquiryRequest);
        ////响应异常判断
       // com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.bean.ResponseHeadDTO responseHead = perBaseInfoInquiryResponse.getResponseHead();
        if (null != perBaseInfoInquiryResponse.getResponseBody()) {

            return perBaseInfoInquiryResponse.getResponseBody().getPerCustBaseInfo();

        }

        return null;


    }

    /**
     * @MathodName doPerBaseInfoInquiryRequest
     * @Description 调ECIF查团体客户基本信息
     * @Author gaoxiang
     * @Params [custNo]
     * @Returns com.ccic.salesapp.customer.outService.esb.ecif.entBaseInfoInquiry.EntCustBaseInfoDTO
     * @Data 2020/3/2
     */
    public  EntCustBaseInfoDTO doEntBaseInfoInquiryRequest(String custNo) {
        //请求实体
        EntBaseInfoInquiryRequest entBaseInfoInquiryRequest = new EntBaseInfoInquiryRequest();
        //请求头
        RequestHeadDTO requestHeadDTO = getRequestHeadDTO();
        entBaseInfoInquiryRequest.setRequestHead(requestHeadDTO);
        //请求体
        EntBaseInfoInquiryRequestDTO entBaseInfoInquiryRequestDTO = new EntBaseInfoInquiryRequestDTO();
        //客户号
        try {
            entBaseInfoInquiryRequestDTO.setCustNo(Long.parseLong(custNo));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //发起请求
//        EntBaseInfoInquiryService entBaseInfoInquiryService = new EntBaseInfoInquiryService();
//        EntBaseInfoInquiry entBaseInfoInquiryEndpoint = entBaseInfoInquiryService.getEntBaseInfoInquiryEndpoint();
         entBaseInfoInquiryRequestDTO.setRole("1001");
        entBaseInfoInquiryRequest.setRequestBody(entBaseInfoInquiryRequestDTO);
        EntBaseInfoInquiryResponse entBaseInfoInquiryResponse = entBaseInfoInquiry.process(entBaseInfoInquiryRequest);
        ////响应异常判断
       // com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.bean.ResponseHeadDTO responseHead = entBaseInfoInquiryResponse.getResponseHead();
        //返回
       // entBaseInfoInquiryResponse.getResponseBody().getEntCustBaseInfo();

        if (null != entBaseInfoInquiryResponse.getResponseBody()) {

            return entBaseInfoInquiryResponse.getResponseBody().getEntCustBaseInfo();

        }

        return null;
    }
}
