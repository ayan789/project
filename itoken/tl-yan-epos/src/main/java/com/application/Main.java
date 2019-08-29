package com.application;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCustomerId("15635242567795284798270661482409");
        addressEntity.setAddressType("1");
        addressEntity.setNumber("0747563534534");
        addressEntity.setProvince("PR65");
        addressEntity.setDistrict("DT781");
        addressEntity.setSubDistrict("SD5044");
        addressEntity.setZipCode("81120");
        addressEntity.setDetail("详细地址");
        addressEntity.setCountry("THA");

        SFund sFund = new SFund();
        sFund.fundId="15669861527354988449776892699250";
        sFund.insureId="15635241955344781400888044271607";
        sFund.fundCode="FUEM";
        sFund.fundPercent="100";
        sFund.fundAllocationType="PREMIUM";
        sFund.higherRisk="N";
        sFund.exchangeRateRisk="Y";
        sFund.fundAMC="UOBAM";

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

        SInformData  informData = new SInformData();
        informData.insureId="15635241955344781400888044271607";

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
        List<SCustomer> beneficiaryList=new ArrayList<>();
        beneficiaryList.add(sCustomer);

        SBeneficiary sBeneficiary = new SBeneficiary();
        sBeneficiary.beneficiaryType="1";
        sBeneficiary.beneficiaryList=beneficiaryList;

        SInsure sInsure = new SInsure();
        sInsure.agentCode="100128"; 
        sInsure.agentName="กรวีร์ ชาตะรัตน์";
        sInsure.branchCode="002";
        sInsure.branchId="0157";
        sInsure.branchName="Phahurad Branch";
        sInsure.caseId="5491";
        sInsure.channel="024";
        sInsure.coverPath="https://prudential-tl-dev.zaouter.com/api/product/file/RU2cover.png";
        sInsure.createTime=new Date();
        sInsure.creator="100128";
        sInsure.familyName="สกุล";
        sInsure.givenName="ชื่อ";
        sInsure.hasTopUpFund="N";
        sInsure.inSubmission="N";
        sInsure.innerContNo="16207100150";
        sInsure.insureDate=new Date();
        sInsure.insureId="15635241955344781400888044271607";
        sInsure.insureNo="205620700150";
        sInsure.insureType="ul";
        sInsure.isDeleted="N";
        sInsure.mainEngineProductId="RU2";
        sInsure.planId="15635241955346699746788525385564";
        sInsure.processStep="step_fna;step_insurant;step_applicant;step_plan;step_benefit;step_insurant_additional;step_applicant_additional;step_beneficary;step_kyccdd_questions;step_health_questions;step_sec_notification;step_fatca;step_lifestyle_questions;step_rpq_questions;step_rpq_analysis;step_premium_fund_allocation;step_uob_information;step_payment;";
        sInsure.productName="PRUHappy Linked";
        sInsure.status=1050;
        sInsure.stepKey="step_payment";
        sInsure.submittedTime="1566986167443";
        sInsure.totalPremium="3000000.00";
        sInsure.updateTime=new Date();
        sInsure.updater="100128";
        sInsure.userId="UOB100128";

        sInsure.pay=spay;
        sInsure.informData=informData;
        sInsure.applicant=sCustomer;
        sInsure.beneficiary=sBeneficiary;

        System.out.println(JSONObject.toJSONString(sInsure));
    }
}
