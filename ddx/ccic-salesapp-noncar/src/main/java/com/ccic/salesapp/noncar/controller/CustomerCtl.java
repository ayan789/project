package com.ccic.salesapp.noncar.controller;

import com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.bean.EntCustBaseInfoDTO;
import com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.bean.PerCustBaseInfoDTO;
import com.ccic.salesapp.noncar.repository.basedb.po.AppCustomer;
import com.ccic.salesapp.noncar.service.CustomerService;
import com.ccic.salessapp.common.core.web.HttpResult;
import com.ccic.salessapp.common.utils.StringUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
@RestController
@Api(tags = "查询客户基本信息", description = "查询客户基本信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "customectl")
public class CustomerCtl {
    @Autowired
    CustomerService customerService;

    @PostMapping("/getCustInfo")
    public HttpResult getCustInfo(String custNo, String custType) {
        try {
            AppCustomer appCustomer = customerService.findCustomer(custNo, custType);
            if (null != appCustomer) {
                return HttpResult.success(appCustomer, 1, "查询成功");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return HttpResult.error(0, "查询客户信息失败");
    }

}
