
package com.ccic.salesapp.noncar.outService.esb.quotetoproposal.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.ccic.salessapp.common.outService.ws.common.bean.RequestHeadDTO;
import com.ccic.salessapp.common.outService.ws.common.bean.ResponseHeadDTO;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccic.service.car.quotetoproposal.bean package. 
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

    private final static QName _RequestBody_QNAME = new QName("http://service.ccic.com/car/quotetoproposal/bean", "requestBody");
    private final static QName _RequestHead_QNAME = new QName("http://service.ccic.com/car/quotetoproposal/bean", "requestHead");
    private final static QName _ResponseBody_QNAME = new QName("http://service.ccic.com/car/quotetoproposal/bean", "responseBody");
    private final static QName _ResponseHead_QNAME = new QName("http://service.ccic.com/car/quotetoproposal/bean", "responseHead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccic.service.car.quotetoproposal.bean
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QuoteToProposalRequestDTO }
     * 
     */
    public QuoteToProposalRequestDTO createQuoteToProposalRequestDTO() {
        return new QuoteToProposalRequestDTO();
    }

    /**
     * Create an instance of {@link QuoteToProposalResponse }
     * 
     */
    public QuoteToProposalResponse createQuoteToProposalResponse() {
        return new QuoteToProposalResponse();
    }

    /**
     * Create an instance of {@link QuoteToProposalResponseDTO }
     * 
     */
    public QuoteToProposalResponseDTO createQuoteToProposalResponseDTO() {
        return new QuoteToProposalResponseDTO();
    }

    /**
     * Create an instance of {@link QuoteToProposalRequest }
     * 
     */
    public QuoteToProposalRequest createQuoteToProposalRequest() {
        return new QuoteToProposalRequest();
    }

    /**
     * Create an instance of {@link SalesDTO }
     * 
     */
    public SalesDTO createSalesDTO() {
        return new SalesDTO();
    }

    /**
     * Create an instance of {@link RelaDTO }
     * 
     */
    public RelaDTO createRelaDTO() {
        return new RelaDTO();
    }

    /**
     * Create an instance of {@link CommissionDTO }
     * 
     */
    public CommissionDTO createCommissionDTO() {
        return new CommissionDTO();
    }

    /**
     * Create an instance of {@link ChannelDTO }
     * 
     */
    public ChannelDTO createChannelDTO() {
        return new ChannelDTO();
    }

    /**
     * Create an instance of {@link ProjectChannelDTO }
     * 
     */
    public ProjectChannelDTO createProjectChannelDTO() {
        return new ProjectChannelDTO();
    }

    /**
     * Create an instance of {@link ComboRationDTO }
     * 
     */
    public ComboRationDTO createComboRationDTO() {
        return new ComboRationDTO();
    }

    /**
     * Create an instance of {@link KindEDTO }
     * 
     */
    public KindEDTO createKindEDTO() {
        return new KindEDTO();
    }

    /**
     * Create an instance of {@link PhoneSalesDTO }
     * 
     */
    public PhoneSalesDTO createPhoneSalesDTO() {
        return new PhoneSalesDTO();
    }

    /**
     * Create an instance of {@link CartaxDTO }
     * 
     */
    public CartaxDTO createCartaxDTO() {
        return new CartaxDTO();
    }

    /**
     * Create an instance of {@link LoanEDTO }
     * 
     */
    public LoanEDTO createLoanEDTO() {
        return new LoanEDTO();
    }

    /**
     * Create an instance of {@link VatInfoDTO }
     * 
     */
    public VatInfoDTO createVatInfoDTO() {
        return new VatInfoDTO();
    }

    /**
     * Create an instance of {@link ComboProductDTO }
     * 
     */
    public ComboProductDTO createComboProductDTO() {
        return new ComboProductDTO();
    }

    /**
     * Create an instance of {@link CheckDTO }
     * 
     */
    public CheckDTO createCheckDTO() {
        return new CheckDTO();
    }

    /**
     * Create an instance of {@link PersonEDTO }
     * 
     */
    public PersonEDTO createPersonEDTO() {
        return new PersonEDTO();
    }

    /**
     * Create an instance of {@link ExtendInfoDTO }
     * 
     */
    public ExtendInfoDTO createExtendInfoDTO() {
        return new ExtendInfoDTO();
    }

    /**
     * Create an instance of {@link EngageDTO }
     * 
     */
    public EngageDTO createEngageDTO() {
        return new EngageDTO();
    }

    /**
     * Create an instance of {@link PrivyDTO }
     * 
     */
    public PrivyDTO createPrivyDTO() {
        return new PrivyDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuoteToProposalRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/car/quotetoproposal/bean", name = "requestBody")
    public JAXBElement<QuoteToProposalRequestDTO> createRequestBody(QuoteToProposalRequestDTO value) {
        return new JAXBElement<QuoteToProposalRequestDTO>(_RequestBody_QNAME, QuoteToProposalRequestDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/car/quotetoproposal/bean", name = "requestHead")
    public JAXBElement<RequestHeadDTO> createRequestHead(RequestHeadDTO value) {
        return new JAXBElement<RequestHeadDTO>(_RequestHead_QNAME, RequestHeadDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuoteToProposalResponseDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/car/quotetoproposal/bean", name = "responseBody")
    public JAXBElement<QuoteToProposalResponseDTO> createResponseBody(QuoteToProposalResponseDTO value) {
        return new JAXBElement<QuoteToProposalResponseDTO>(_ResponseBody_QNAME, QuoteToProposalResponseDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseHeadDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ccic.com/car/quotetoproposal/bean", name = "responseHead")
    public JAXBElement<ResponseHeadDTO> createResponseHead(ResponseHeadDTO value) {
        return new JAXBElement<ResponseHeadDTO>(_ResponseHead_QNAME, ResponseHeadDTO.class, null, value);
    }

}
