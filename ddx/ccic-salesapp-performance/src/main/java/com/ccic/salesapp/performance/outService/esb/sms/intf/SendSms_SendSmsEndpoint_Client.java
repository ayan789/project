
package com.ccic.salesapp.performance.outService.esb.sms.intf;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import com.ccic.salesapp.performance.outService.esb.sms.bean.SendSmsRequest;
import com.ccic.salesapp.performance.outService.esb.sms.bean.SendSmsResponse;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */


/**
 * This class was generated by Apache CXF 2.7.0
 * 2017-08-16T10:10:09.302+08:00
 * Generated source version: 2.7.0
 * 
 */
public final class SendSms_SendSmsEndpoint_Client {

    private static final QName SERVICE_NAME = new QName("http://service.ccic.com/sms/sendSms/intf", "SendSmsService");

    private SendSms_SendSmsEndpoint_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SendSmsService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        SendSmsService ss = new SendSmsService(wsdlURL, SERVICE_NAME);
        SendSms port = ss.getSendSmsEndpoint();  
        
        {
        System.out.println("Invoking sendSms...");
        SendSmsRequest _sendSms_sendSmsRequest = null;
        SendSmsResponse _sendSms__return = port.sendSms(_sendSms_sendSmsRequest);
        System.out.println("sendSms.result=" + _sendSms__return);


        }

        System.exit(0);
    }

}