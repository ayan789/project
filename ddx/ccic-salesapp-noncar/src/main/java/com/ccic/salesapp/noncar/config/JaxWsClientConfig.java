package com.ccic.salesapp.noncar.config;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import com.ccic.salesapp.noncar.outService.esb.EInvoiceApplication.intf.EinvoiceApplicationForApp;
import com.ccic.salesapp.noncar.outService.esb.InvoiceLink.intf.InvoiceLinkQueryPort;
import com.ccic.salesapp.noncar.outService.esb.PaymentNoApply.PaymentNoApply;
import com.ccic.salesapp.noncar.outService.esb.balancequery.BalanceQueryPort;
import com.ccic.salesapp.noncar.outService.esb.einvoiceInquiry.intf.EinvoiceInquiryServicePort;
import com.ccic.salesapp.noncar.outService.esb.entBaseInfoInquiry.intf.EntBaseInfoInquiry;
import com.ccic.salesapp.noncar.outService.esb.h5imgquerydown.intf.H5ImgQueryDownPort;
import com.ccic.salesapp.noncar.outService.esb.paysuccess.PaySuccessNoticePort;
import com.ccic.salesapp.noncar.outService.esb.perBaseInfoInquiry.intf.PerBaseInfoInquiry;
import com.ccic.salesapp.noncar.outService.esb.quoteprice.intf.QuotePrice;
import com.ccic.salesapp.noncar.outService.esb.shorturl.intf.ShortUrlPort;
import com.ccic.salesapp.noncar.outService.esb.sms.intf.SendSms;
import com.ccic.salesapp.noncar.outService.esb.userservice.userinquiry.intf.UserInquiryService;
import com.ccic.salesapp.noncar.outService.esb.withholdorder.WithholdOrderPort;
import com.ccic.salesapp.noncar.outService.esb.withholdstatus.WithholdStatusPort;
import com.ccic.salessapp.common.utils.WSUtil;

@Configuration
public class JaxWsClientConfig {

	@Value("${esb.address.sendsms}")
	String sendSms;

	@Value("${esb.address.userInquiryService}")
	String userInquiryService;

	@Value("${esb.address.quotePrice}")
	String quotePrice;
	
	@Value("${esb.address.h5ImgQueryDownPort}")
	String h5ImgQueryDownPort;
	
	@Value("${esb.address.paymentNoApply}")
	String paymentNoApply;
	
//	@Value("${esb.address.einvoiceApplicationForApp}")
//	String einvoiceApplicationForApp;
	
	@Value("${esb.address.invoiceLinkQueryPort}")
	String invoiceLinkQueryPort;
	
	@Value("${esb.address.einvoiceInquiry}")
	String einvoiceInquiry;
	
	@Value("${esb.address.shorturl}")
	String shorturl;
	
	@Value("${esb.address.perBaseInfoInquiryService}")
	String perBaseInfoInquiryService;
	
	@Value("${esb.address.entBaseInfoInquiryService}")
	String entBaseInfoInquiryService;
	
	@Value("${esb.address.balanceQueryPort}")
	String balanceQueryPort;
	
	@Value("${esb.address.paySuccessNoticePort}")
	String  paySuccessNoticePort;
	
	@Value("${esb.address.withholdOrderPort}")
	String  withholdOrderPort;
	
	@Value("${esb.address.withholdStatusPort}")
	String  withholdStatusPort;
	
	@Value("${esb.address.PerCustNoSearchService}")
	String  PerCustNoSearchService;

	@Bean
	public JaxWsPortProxyFactoryBean userInquiryService() throws MalformedURLException {
		return WSUtil.create(userInquiryService, UserInquiryService.class);
	}

	@Bean
	public JaxWsPortProxyFactoryBean quotePrice() throws MalformedURLException {
		return WSUtil.create(quotePrice, QuotePrice.class);
	}

	@Bean
	public JaxWsPortProxyFactoryBean sendSms() throws MalformedURLException {
		return WSUtil.create(sendSms, SendSms.class);
	}
	
	@Bean
    public JaxWsPortProxyFactoryBean h5ImgQueryDownPort() throws MalformedURLException {
    	return WSUtil.create(h5ImgQueryDownPort, H5ImgQueryDownPort.class);
    }
	
	@Bean
	public JaxWsPortProxyFactoryBean paymentNoApply() throws MalformedURLException {
		return WSUtil.create(paymentNoApply, PaymentNoApply.class);
	}

	@Bean
	public JaxWsPortProxyFactoryBean invoiceLinkQueryPort() throws MalformedURLException {
		return WSUtil.create(invoiceLinkQueryPort,
				InvoiceLinkQueryPort.class);
	}

	@Bean
	public JaxWsPortProxyFactoryBean einvoiceInquiry() throws MalformedURLException {
		return WSUtil.create(einvoiceInquiry, EinvoiceInquiryServicePort.class);
	}

	@Bean
	public JaxWsPortProxyFactoryBean shorturl() throws MalformedURLException {
		return WSUtil.create(shorturl, ShortUrlPort.class);
	}


	@Bean
	public JaxWsPortProxyFactoryBean perBaseInfoInquiryService() throws MalformedURLException {
		return WSUtil.create(perBaseInfoInquiryService, PerBaseInfoInquiry.class);
	}

	@Bean
	public JaxWsPortProxyFactoryBean entBaseInfoInquiryService() throws MalformedURLException {
		return WSUtil.create(entBaseInfoInquiryService, EntBaseInfoInquiry.class);
	}
	
	@Bean
	public JaxWsPortProxyFactoryBean balanceQueryPort() throws MalformedURLException {
		return WSUtil.create(balanceQueryPort, BalanceQueryPort.class);
	}
	
 
	@Bean
    public JaxWsPortProxyFactoryBean withholdOrderPort() throws MalformedURLException {
     return WSUtil.create(withholdOrderPort, WithholdOrderPort.class);
    }
 
	
	@Bean 
	public JaxWsPortProxyFactoryBean paySuccessNoticePort() throws MalformedURLException { 
		return WSUtil.create(paySuccessNoticePort,	PaySuccessNoticePort.class);
	}
	
 
	@Bean
    public JaxWsPortProxyFactoryBean withholdStatusPort() throws MalformedURLException {
     return WSUtil.create(withholdStatusPort,  WithholdStatusPort.class);
    }
	
}
