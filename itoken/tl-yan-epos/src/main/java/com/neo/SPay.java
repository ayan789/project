package com.neo;


import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.Date;

/**
 * Created by yangzhilei on 2017/10/19.
 * 支付信息
 */
@Data
public class SPay {

    public static final String AES_KEY = "DFJGKDHGKJDHJDNCMV";

    public static final String CREDIT_CARD = "3";

    public static final String CASH_PLUS = "4";

    public static final String DIRECT_DEBIT_BANK_ACCOUNT = "5";

    public static final String SIX_MONTH = "6";

    public static final String TEN_MONTH = "10";

    public String insureId;

    public String payment;
    public String paymentMethod;

    public String annualPremium;

    public String bankName;

    public String creditCardRelationship;

    public String cardTypes;

    public String ownerName;

    public String cardNumber;

    public String expiryYear;

    public String expiryMonth;

    public String renewalBankName;

    public String renewalCardTypes;

    public String renewalOwnerName;

    public String renewalCardNumber;

    public String renewalExpiryYear;

    public String renewalExpiryMonth;

    public String renewalAccountName;

    public String renewalAccountNumber;

    public String renewalPremium;

    public String renewalPayment;

    public String renewalRelationship;

    public String renewalFullInstallment;

    public String renewalInstallmentPlan;

    public String renewalInstallmentPlanType;

    public String renewalInstallmentNewPlanType;

    public String renewalInstallmentNewPlan;

    public String dividendOption;

    public String accountBelongsTo;

    public String relationship;

    public String dividendBank;

    public String dividendBranch;

    public String dividendAccountName;

    public String dividendAccountNumber;

    public String firstRefId;

    public String firstRefName;

    public String firstRefFirstName;

    public String firstRefLastName;

    public String secondRefId;

    public String secondRefName;

    public String secondRefFirstName;

    public String secondRefLastName;

    public String uobBranch;
    public String uplineManagerCode;

    public String campaign;

    public String dividend;

    public String accountName;

    public String accountNumber;

    public String directDebitTypes;

    public String installmentPlan;

    public String installmentPlanType;

    public String fullInstallment;

    public String installmentNewPlanType;

    public String installmentNewPlan;

    public String firstPaymentDate;
    public String renewalFirstPaymentDate;

    public String topUpPaymentMethod;
    public String topUpPremium;
    public String topUpBankName;
    public String topUpRelationship;
    public String topUpCardTypes;
    public String topUpAccountName;
    public String topUpAccountNumber;
    public String topUpOnlinePayment;

    public String onlinePayment;
   // public String renewalOnlinePayment;
    public String renewalPaymentMethod;

    String formatString(Date date) {
        if (date == null) {
            return null;
        }
        return DateUtils.DateToString(date, "yyyy-MM");
    }


}
