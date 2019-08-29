package com.example.tlyanepos;

import com.application.SBeneficiary;
import com.application.SCustomer;
import com.application.SInformData;
import com.application.SPay;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SInsureServiceImpl implements SInsureService {

    @Override
    public SPay getSpay(Long userId) {
        SPay spay = new SPay();
        spay.accountBelongsTo="3";
        spay.bankName="024";
        spay.campaign="No";
        spay.cardNumber="KgCfxI1alBxDqq9nnmmKsKfUIrRvbp7Xkfg9f3/KZIw=";
        spay.cardTypes="1";
        spay.creditCardRelationship="Parents";
        spay.dividendAccountName="MISS ชื่อ สกุล";
        spay.dividendAccountNumber="g8BqxqhS48GtsW7UFtzmXwhHcQlxkWYoNt+0VXieBlU=";
        spay.dividendBank="002";
        spay.dividendOption="4";
        spay.expiryYear="yl75JmXbFMtTUTdH05boFVgjb+eXcxC+ntLIqUZPNAk=";
        spay.firstPaymentDate="30-07-2562";
        spay.fullInstallment="1";
        spay.insureId="15635241955344781400888044271607";
        spay.offSiteService="N";
        spay.onlinePayment="Y";
        spay.ownerName="rty";
        spay.paymentMethod="3";
        spay.renewalPayment="3";
        spay.topUpPaymentMethod="";
        spay.uobBranch="002";
        spay.uplineManagerCode="002";
        return spay;
    }

    @Override
    public SInformData getSInformData(Long userId) {
        SInformData  informData = new SInformData();
        informData.insureId="15635241955344781400888044271607";
        return informData;
    }

    @Override
    public SCustomer getScustomer(Long userId) {
        SCustomer sCustomer = new SCustomer();
        sCustomer.addressType="1";
        sCustomer.age="30";
        sCustomer.benefitRatio="100";
        sCustomer.customerId="15635243875277331847396693600168";
        sCustomer.customerType="3";
        sCustomer.familyName="rw";
        sCustomer.givenName="ere";
        sCustomer.insureId="15635241955344781400888044271607";
        sCustomer.isMarriedRegister="";
        sCustomer.isPayor="N";
        sCustomer.moreNationality="";
        sCustomer.relationCode="Wife";
        sCustomer.title="นาง";
        sCustomer.uniqueId="15635241955344781400888044271607_3_0";
        return sCustomer;
    }

    @Override
    public SBeneficiary getSBeneficiary(Long userId) {

        SCustomer sCustomer = new SCustomer();
        sCustomer.addressType="1";
        sCustomer.age="30";
        sCustomer.benefitRatio="100";
        sCustomer.customerId="15635243875277331847396693600168";
        sCustomer.customerType="3";
        sCustomer.familyName="rw";
        sCustomer.givenName="ere";
        sCustomer.insureId="15635241955344781400888044271607";
        sCustomer.isMarriedRegister="";
        sCustomer.isPayor="N";
        sCustomer.moreNationality="";
        sCustomer.relationCode="Wife";
        sCustomer.title="นาง";
        sCustomer.uniqueId="15635241955344781400888044271607_3_0";
        List<SCustomer> list = new ArrayList<>();
        list.add(sCustomer);
        list.add(sCustomer);
        SBeneficiary sBeneficiary = new SBeneficiary();
        sBeneficiary.beneficiaryList=list;
        sBeneficiary.beneficiaryType="1";
        return sBeneficiary;
    }
}
