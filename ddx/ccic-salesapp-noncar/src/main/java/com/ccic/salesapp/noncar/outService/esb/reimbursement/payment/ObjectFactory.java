
package com.ccic.salesapp.noncar.outService.esb.reimbursement.payment;

import javax.xml.bind.annotation.XmlRegistry;

import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesAgentMsgDto;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesAgreementMsgDto;
import com.ccic.salesapp.noncar.outService.esb.reimbursement.payment.base.dto.custom.SalesShadowAgentMsgDto;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.salesapp.order.outService.esb.reimbursement.payment package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.salesapp.order.outService.esb.reimbursement.payment
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResponseHeadDTO }
     * 
     */
    public ResponseHeadDTO createResponseHeadDTO() {
        return new ResponseHeadDTO();
    }

    /**
     * Create an instance of {@link RequestHeadDTO }
     * 
     */
    public RequestHeadDTO createRequestHeadDTO() {
        return new RequestHeadDTO();
    }

    /**
     * Create an instance of {@link AgentAgreeInquiryRequest }
     * 
     */
    public AgentAgreeInquiryRequest createAgentAgreeInquiryRequest() {
        return new AgentAgreeInquiryRequest();
    }

    /**
     * Create an instance of {@link AgentAgreeInquiryRequestDTO }
     * 
     */
    public AgentAgreeInquiryRequestDTO createAgentAgreeInquiryRequestDTO() {
        return new AgentAgreeInquiryRequestDTO();
    }

    /**
     * Create an instance of {@link AgentAgreeInquiryResponse }
     * 
     */
    public AgentAgreeInquiryResponse createAgentAgreeInquiryResponse() {
        return new AgentAgreeInquiryResponse();
    }

    /**
     * Create an instance of {@link AgentAgreeInquiryResponseDTO }
     * 
     */
    public AgentAgreeInquiryResponseDTO createAgentAgreeInquiryResponseDTO() {
        return new AgentAgreeInquiryResponseDTO();
    }

    /**
     * Create an instance of {@link SalesAgreementMsgDto }
     * 
     */
    public SalesAgreementMsgDto createSalesAgreementMsgDto() {
        return new SalesAgreementMsgDto();
    }

    /**
     * Create an instance of {@link SalesShadowAgentMsgDto }
     * 
     */
    public SalesShadowAgentMsgDto createSalesShadowAgentMsgDto() {
        return new SalesShadowAgentMsgDto();
    }

    /**
     * Create an instance of {@link SalesAgentMsgDto }
     * 
     */
    public SalesAgentMsgDto createSalesAgentMsgDto() {
        return new SalesAgentMsgDto();
    }

}
