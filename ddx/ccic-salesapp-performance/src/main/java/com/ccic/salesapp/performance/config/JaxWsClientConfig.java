package com.ccic.salesapp.performance.config;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import com.ccic.salesapp.performance.outService.esb.PaymentNoApply.PaymentNoApply;
import com.ccic.salesapp.performance.outService.esb.PlanFeeInquiry.PlanFeeInquiryPort;
import com.ccic.salesapp.performance.outService.esb.Withdrawals.WithdrawalsPort;
import com.ccic.salesapp.performance.outService.esb.sms.intf.SendSms;
import com.ccic.salessapp.common.utils.WSUtil;


@Configuration
public class JaxWsClientConfig {
	
	@Value("${esb.address.planFeeInquiryServiceURL}")
	private String planFeeInquiryServiceURL;
	
	@Value("${esb.address.withdrawalsServiceURL}")
	private String withdrawalsServiceURL;

	@Value("${esb.address.sendSmsServiceURL}")
	private String sendSmsServiceURL;
	
	@Value("${esb.address.paymentNoApplyServiceURL}")
	private String paymentNoApplyServiceURL;

	@Bean
    public JaxWsPortProxyFactoryBean planFeeInquiryPort() throws MalformedURLException {
    	return WSUtil.create(planFeeInquiryServiceURL, "PlanFeeInquiryService","PlanFeeInquiryPort", PlanFeeInquiryPort.class);
    }
	
	@Bean
    public JaxWsPortProxyFactoryBean withdrawalsPort() throws MalformedURLException {
    	return WSUtil.create(withdrawalsServiceURL, "WithdrawalsService","WithdrawalsPort", WithdrawalsPort.class);
    }

	@Bean
    public JaxWsPortProxyFactoryBean sendSms() throws MalformedURLException {
    	return WSUtil.create(sendSmsServiceURL, "SendSmsService","SendSmsEndpoint", SendSms.class);
    }

	@Bean
    public JaxWsPortProxyFactoryBean paymentNoApply() throws MalformedURLException {
    	return WSUtil.create(paymentNoApplyServiceURL, "PaymentNoApplyService","PaymentNoApplyEndpoint", PaymentNoApply.class);
    }
    
    
}
