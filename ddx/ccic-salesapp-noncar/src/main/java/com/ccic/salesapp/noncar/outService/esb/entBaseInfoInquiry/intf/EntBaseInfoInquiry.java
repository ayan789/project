package com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.intf;

import com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.bean.EntBaseInfoInquiryRequest;
import com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.bean.EntBaseInfoInquiryResponse;
import com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.bean.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.3.5
 * 2020-03-02T15:42:49.420+08:00
 * Generated source version: 3.3.5
 *
 */
@WebService(targetNamespace = "http://service.ccic.com/ecif/entbaseinfoinquiry/intf", name = "EntBaseInfoInquiry")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface EntBaseInfoInquiry {

    @WebMethod(action = "/ecif/entbaseinfoinquiry")
    @WebResult(name = "EntBaseInfoInquiryResponse", targetNamespace = "http://service.ccic.com/ecif/entbaseinfoinquiry/bean", partName = "EntBaseInfoInquiryResponse")
    public EntBaseInfoInquiryResponse process(

            @WebParam(partName = "EntBaseInfoInquiryRequest", name = "EntBaseInfoInquiryRequest", targetNamespace = "http://service.ccic.com/ecif/entbaseinfoinquiry/bean")
                    EntBaseInfoInquiryRequest entBaseInfoInquiryRequest
    );
}
